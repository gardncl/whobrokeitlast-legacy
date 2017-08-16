package isaacearl.oauth.two.provider;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import isaacearl.contract.ProviderContract;
import isaacearl.contract.UserContract;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

abstract class AbstractProvider implements ProviderContract {


    private HttpServletRequest request;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;

    protected boolean stateless = false;

    protected String scopeSeparator = ",";

    AbstractProvider(HttpServletRequest request, String clientId, String clientSecret, String redirectUrl) {
        this.request = request;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }

    @Override
    public RedirectView redirect() {

        String state = null;

        if (this.usesState()) {
            this.request.getSession().setAttribute("state", state = this.getState());
        }

        return new RedirectView(this.getAuthUrl(state));
    }

    protected abstract String getAuthUrl(String state);

    private String getState() {
        return RandomStringUtils.randomAlphanumeric(40).toUpperCase();
    }

    private boolean usesState() {
        return !this.stateless;
    }

    private boolean isStateless() {
        return stateless;
    }

    /**
     * Get the authentication URL for the provider
     *
     * @param url   the url
     * @param state string representing state
     * @return the full url
     */
    protected String buildAuthUrlFromBase(String url, String state) {
        return url + '?' + URLEncodedUtils.format(this.getCodeFields(state), Charset.defaultCharset());
    }

    protected List<NameValuePair> getCodeFields(String state) {
        List<NameValuePair> codeFields = new ArrayList<>();

        codeFields.add(new BasicNameValuePair("client_id", this.clientId));
        codeFields.add(new BasicNameValuePair("redirect_uri", this.redirectUrl));
        codeFields.add(new BasicNameValuePair("scope", this.formatScopes(this.getScopes(), this.scopeSeparator)));
        codeFields.add(new BasicNameValuePair("response_type", "code"));

        // only sometimes
        if (this.usesState()) {
            codeFields.add(new BasicNameValuePair("state", state));
        }

        return codeFields;
    }

    protected List<NameValuePair> getTokenFields(String code) {
        List<NameValuePair> tokenFields = new ArrayList<>();

        tokenFields.add(new BasicNameValuePair("client_id", this.clientId));
        tokenFields.add(new BasicNameValuePair("redirect_uri", this.redirectUrl));
        tokenFields.add(new BasicNameValuePair("code", code));
        tokenFields.add(new BasicNameValuePair("client_secret", this.clientSecret));

        return tokenFields;
    }

    protected abstract List<String> getScopes();

    private String formatScopes(List<String> scopes, String scopeSeparator) {
        StringJoiner stringJoiner = new StringJoiner(scopeSeparator);
        for (String scope : scopes) {
            stringJoiner.add(scope);
        }
        return stringJoiner.toString();
    }

    protected boolean hasInvalidState() {
        if (this.isStateless()) {
            return false;
        }

        String state = String.valueOf(this.request.getSession().getAttribute("state"));

        return !(StringUtils.length(state) > 0 && this.request.getParameter("state").equals(state));
    }

    protected String getCode() {
        return this.request.getParameter("code");
    }

    public String getAccessToken(String code) throws UnirestException, JSONException {

        HttpRequestWithBody postRequest = Unirest.post(this.getTokenUrl())
            .header("Accept", "application/json");

        postRequest.fields(this.convertToMap(this.getTokenFields(code)));

        return postRequest.asJson().getBody().getObject().getString("access_token");
    }

    private Map<String, Object> convertToMap(List<NameValuePair> tokenFields) {

        Map<String, Object> map = new HashMap<>();
        for (NameValuePair nameValuePair : tokenFields) {
            map.put(nameValuePair.getName(), nameValuePair.getValue());
        }

        return map;
    }

    public AbstractProvider stateless() {
        this.stateless = true;
        return this;
    }

    protected abstract String getTokenUrl();

    protected abstract UserContract getUserByAccessToken(String accessToken) throws UnirestException, JSONException;

    protected abstract UserContract mapUserJsonObjectToProviderUser(JSONObject userJsonObject) throws JSONException;
}
