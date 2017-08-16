package isaacearl.contract;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.web.servlet.view.RedirectView;

public interface ProviderContract {

    RedirectView redirect();

    UserContract user() throws UnirestException, JSONException;

    ProviderContract stateless();
}
