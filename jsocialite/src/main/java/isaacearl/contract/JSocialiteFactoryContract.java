package isaacearl.contract;


import isaacearl.oauth.two.provider.ProviderEnum;

public interface JSocialiteFactoryContract {

    public ProviderContract provider();

    public ProviderContract provider(ProviderEnum driver);

}
