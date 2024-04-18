package io.github.mateuussilvapb.app_corretores.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class Oauth2ResourceServerConfigurer {
    static void configure(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        configurer.jwt(Oauth2ResourceServerConfigurer::configureJwt);
    }

    private static void configureJwt(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer configurer) {
        configurer.jwtAuthenticationConverter(Oauth2ResourceServerConfigurer::withAuthorities);
    }

    private static AbstractAuthenticationToken withAuthorities(Jwt jwt) {
        return new JwtAuthenticationToken(jwt, ClaimsRolesExtractor.extract(jwt));
    }
}
