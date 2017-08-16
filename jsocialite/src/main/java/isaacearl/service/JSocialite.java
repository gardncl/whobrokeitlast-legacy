package isaacearl.service;


import isaacearl.config.JSocialiteProperties;
import isaacearl.contract.JSocialiteFactoryContract;
import isaacearl.contract.ProviderContract;
import isaacearl.oauth.two.provider.GithubProvider;
import isaacearl.oauth.two.provider.ProviderEnum;

import javax.servlet.http.HttpServletRequest;

public class JSocialite implements JSocialiteFactoryContract {

    private final HttpServletRequest request;
    private JSocialiteProperties config;

    public JSocialite(HttpServletRequest request, JSocialiteProperties config) {
        this.request = request;
        this.config = config;
    }

    @Override
    public ProviderContract provider() {
        return this.provider(ProviderEnum.GITHUB);
    }

    @Override
    public ProviderContract provider(ProviderEnum driver) {

        switch (driver) {
            case GITHUB:
                return new GithubProvider(this.request, config.getGithub().getClientId(), config.getGithub().getClientSecret(), config.getGithub().getRedirectUrl());
            default:
                return new GithubProvider(this.request, config.getGithub().getClientId(), config.getGithub().getClientSecret(), config.getGithub().getRedirectUrl());
        }

    }
}
