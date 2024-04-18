package io.github.mateuussilvapb.app_corretores.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//Habilita o uso do '@PreAuthorize' em toda a aplicação
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] ALLOW_ACCESS = {"/h2-console/**"};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())))
                .headers(headers -> headers.frameOptions().sameOrigin()) // Allow same origin to frame our site
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(ALLOW_ACCESS).permitAll());
        return http.build();
    }
}
