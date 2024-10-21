package ru.invaders.keycloak.authenticators;

import org.keycloak.Config;
import org.keycloak.OAuth2Constants;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordFormFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.authentication.authenticators.AttemptedAuthenticator;
import static org.keycloak.provider.ProviderConfigProperty.STRING_TYPE;
import static java.util.Arrays.asList;
import java.util.List;

public class MattermostIntegrationAuthenticatorFactory extends UsernamePasswordFormFactory {

    public static final String PROVIDER_ID  = "arenadata-platform";
    public static final String GITLAB_TOKEN = "gitlabToken";
    public static final String GITLAB_URI   = "gitlabURI";

    @Override
    public Authenticator create(KeycloakSession session) {
        return new MattermostIntegrationAuthenticator();
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return true;
    }

    @Override
    public String getDisplayType() {
        return "Arenadata Platform Integration.";
    }

    @Override
    public String getHelpText() {
        return "Arenadata Platform Integration.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        ProviderConfigProperty gitlab_uri = new ProviderConfigProperty(
            GITLAB_URI,
            "Gitlab URI",
            "Gitlab instance URI (https://gitlab.com)",
            STRING_TYPE,
            null
        );
        ProviderConfigProperty gitlab_token = new ProviderConfigProperty(
            GITLAB_TOKEN, 
            "Gitlab Token", 
            "Gitlab private token", 
            STRING_TYPE, 
            null
        );
        return asList(gitlab_uri, gitlab_token);
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
