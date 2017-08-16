package isaacearl.oauth.two.provider;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import isaacearl.oauth.two.User;
import isaacearl.oauth.two.exception.InvalidStateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class GithubProvider extends AbstractProvider {

    private List<String> scopes = Collections.singletonList("user:email");

    public GithubProvider(HttpServletRequest request, String clientId, String clientSecret, String redirect) {
        super(request, clientId, clientSecret, redirect);
    }

    @Override
    protected String getAuthUrl(String state) {
        return this.buildAuthUrlFromBase("http://github.com/login/oauth/authorize", state);
    }

    protected String getTokenUrl() {
        return "https://github.com/login/oauth/access_token";
    }

    @Override
    protected User getUserByAccessToken(String accessToken) throws UnirestException, JSONException {

        String userUrl = "https://api.github.com/user?access_token=" + accessToken;

        JSONObject userJsonObject = Unirest.get(userUrl)
            .header("Accept", "application/vnd.github.v3+json").asJson().getBody().getObject();

        if (this.getScopes().contains("user:email")) {
            try {
                userJsonObject.put("email", this.getEmailByAccessToken(accessToken));
            } catch (JSONException e) {
                // i intentionally do nothing here...
                // we are just trying to get a verified, primary email if we can.
            }
        }

        return this.mapUserJsonObjectToProviderUser(userJsonObject);

    }

    @Override
    protected User mapUserJsonObjectToProviderUser(JSONObject userJsonObject) throws JSONException {
        User user = new User();
        user.setId(userJsonObject.getString("id"));
        user.setNickName(userJsonObject.getString("login"));
        user.setName(userJsonObject.getString("name"));
        user.setEmail(userJsonObject.getString("email"));
        user.setAvatar(userJsonObject.getString("avatar_url"));

        return user;
    }

    protected String getEmailByAccessToken(String accessToken) throws UnirestException, JSONException {
        String emailUrl = "https://api.github.com/user/emails?access_token=" + accessToken;

        JSONArray emailJsonArray = Unirest.get(emailUrl)
            .header("Accept", "application/vnd.github.v3+json").asJson().getBody().getArray();

        for (int i = 0; i < emailJsonArray.length(); i++) {
            JSONObject emailJsonObject = emailJsonArray.getJSONObject(i);
            if (emailJsonObject.getBoolean("primary") && emailJsonObject.getBoolean("verified")) {
                return emailJsonObject.getString("email");
            }
        }

        return null;

//            $response = $this->getHttpClient()->get(
//                $emailsUrl, $this->getRequestOptions()
//            );
    }

    @Override
    protected List<String> getScopes() {
        return this.scopes;
    }

    @Override
    public User user() throws UnirestException, JSONException {

        if (this.hasInvalidState()) {
            throw new InvalidStateException("Invalid State!");
        }

        String accessToken = this.getAccessToken(this.getCode());

        return this.getUserByAccessToken(accessToken)
            .setToken(accessToken);

    }

}
