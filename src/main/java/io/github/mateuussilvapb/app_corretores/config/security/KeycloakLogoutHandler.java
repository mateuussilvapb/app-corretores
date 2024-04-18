package io.github.mateuussilvapb.app_corretores.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class KeycloakLogoutHandler implements LogoutHandler {

    private static final String TOKEN = "token";
    private static final String ID_TOKEN_HINT = "id_token_hint";
    private static final String LOGOUT_PATH = "/protocol/openid-connect/logout";

    private final URI sessionEndpoint;
    private final RestTemplate restTemplate = new RestTemplate();

    public KeycloakLogoutHandler(@Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}") String issuer)
            throws URISyntaxException {
        this.sessionEndpoint = new URI(issuer + LOGOUT_PATH);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        AuthenticationResolverFacade.resolve(auth)
                .flatMap(usuario -> {
                    log.info(">>> {} logging out", usuario.login());
                    return usuario.customValueString(TOKEN);
                })
                .ifPresent(this::logoutFromKeycloak);
    }

    private void logoutFromKeycloak(String token) {
        final var response = restTemplate.getForEntity(uri(token), String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("<<< Successfulley logged out from Keycloak");
        } else {
            log.error("<<< Could not propagate logout to Keycloak");
        }
    }

    private URI uri(String idToken) {
        return UriComponentsBuilder.fromUri(sessionEndpoint).queryParam(ID_TOKEN_HINT, idToken).build().toUri();
    }
}
