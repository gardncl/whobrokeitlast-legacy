package gardncl.whobrokeitlast.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import isaacearl.config.JSocialiteProperties;
import isaacearl.contract.UserContract;
import isaacearl.oauth.two.provider.ProviderEnum;
import isaacearl.service.JSocialite;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private JSocialite jSocialite;

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public RedirectView redirect(HttpServletRequest httpServletRequest) {

        JSocialiteProperties jSocialiteProperties = new JSocialiteProperties();
        jSocialiteProperties.getGithub().setClientId("xxx");
        jSocialiteProperties.getGithub().setClientSecret("xxx");
        jSocialiteProperties.getGithub().setRedirectUrl("xxx");

        jSocialite = new JSocialite(httpServletRequest, jSocialiteProperties); //

        return jSocialite.provider(ProviderEnum.GITHUB).stateless().redirect();
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void handleCallback(HttpServletRequest httpServletRequest) throws UnirestException {

        JSocialiteProperties jSocialiteProperties = new JSocialiteProperties();
        jSocialiteProperties.getGithub().setClientId("xxx");
        jSocialiteProperties.getGithub().setClientSecret("xxx");
        jSocialiteProperties.getGithub().setRedirectUrl("xxx");
        jSocialite = new JSocialite(httpServletRequest, jSocialiteProperties); //

        UserContract user = jSocialite.provider(ProviderEnum.GITHUB).stateless().user();

        // you are no authenticated
        //

    }
}
