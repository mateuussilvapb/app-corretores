package io.github.mateuussilvapb.app_corretores.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.stereotype.Component;

@Component
class AppLogoutConfigurer {

    private static final String LOGOUT_URL = "/logout";

    private final String postLogoutUrl;
    private final KeycloakLogoutHandler keycloakLogoutHandler;

    AppLogoutConfigurer(@Value("${app.post-logout-url}") String postLogoutUrl,
                        KeycloakLogoutHandler keycloakLogoutHandler) {
        this.postLogoutUrl = postLogoutUrl;
        this.keycloakLogoutHandler = keycloakLogoutHandler;
    }

    void configure(LogoutConfigurer<HttpSecurity> configurer) {
        configurer.logoutUrl(LOGOUT_URL).addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl(postLogoutUrl);
    }
}