package io.github.mateuussilvapb.app_corretores.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;


@Component
@RequiredArgsConstructor
public class AppCorsConfigurer {


    void configure(CorsConfigurer<HttpSecurity> configurer) {
        configurer.configurationSource(corsConfigurationSource());
    }

    CorsConfigurationSource corsConfigurationSource() {
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", publicConfiguration());
        return source;
    }

    private CorsConfiguration publicConfiguration() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        return configuration;
    }
}
