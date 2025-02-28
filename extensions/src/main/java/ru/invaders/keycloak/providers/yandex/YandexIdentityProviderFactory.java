package ru.invaders.keycloak.providers.yandex;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

import java.util.List;

public class YandexIdentityProviderFactory
    extends AbstractIdentityProviderFactory<YandexIdentityProvider>
    implements SocialIdentityProviderFactory<YandexIdentityProvider> {

    public static final String PROVIDER_ID = "yandex";

    @Override
    public String getName() {
        return "Yandex";
    }

    @Override
    public YandexIdentityProvider create(
       final KeycloakSession session, final IdentityProviderModel model
    ) {
        return new YandexIdentityProvider(session, new YandexIdentityProviderConfig(model));
    }

    @Override
    public YandexIdentityProviderConfig createConfig() {
        return new YandexIdentityProviderConfig();
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property()
                .name("hostedDomain")
                .label("Hosted domains")
                .helpText("Comma ',' separated list of domains is supported.")
                .type(ProviderConfigProperty.STRING_TYPE)
                .add()
                .build();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

}
