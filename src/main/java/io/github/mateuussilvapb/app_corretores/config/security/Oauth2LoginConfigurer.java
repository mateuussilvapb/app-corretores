package io.github.mateuussilvapb.app_corretores.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Oauth2LoginConfigurer {

    private final KeycloakAuthorityMapper keycloakAuthorityMapper;

    void configure(OAuth2LoginConfigurer<HttpSecurity> configurer) {
        configurer.userInfoEndpoint(this::getUserAuthoritiesMapper);
    }

    private void getUserAuthoritiesMapper(OAuth2LoginConfigurer<HttpSecurity>.UserInfoEndpointConfig config) {
        config.userAuthoritiesMapper(keycloakAuthorityMapper);
    }
}