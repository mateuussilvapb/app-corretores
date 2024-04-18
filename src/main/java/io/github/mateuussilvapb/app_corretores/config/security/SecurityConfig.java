package io.github.mateuussilvapb.app_corretores.config.security;

import io.github.mateuussilvapb.app_corretores.infra.usuario.PerfilUsuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private static final String ADMIN_ROUTES = "/admin/**";
    private static final String[] PUBLIC_RESOURCES = {
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/public/**",
    };

    private final AppCorsConfigurer corsConfigurer;
    private final AppLogoutConfigurer logoutConfigurer;
    private final Oauth2LoginConfigurer oauth2LoginConfigurer;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(corsConfigurer::configure)
                .securityMatcher("/**")
                .authorizeHttpRequests(SecurityConfig::configureRoutes)
                .oauth2Login(oauth2LoginConfigurer::configure)
                .logout(logoutConfigurer::configure)
                .oauth2ResourceServer(Oauth2ResourceServerConfigurer::configure)
                .build();
//        http.csrf(AbstractHttpConfigurer::disable)
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())))
//                .headers(headers -> headers.frameOptions().sameOrigin()) // Allow same origin to frame our site
//                .authorizeHttpRequests(authorize -> authorize.requestMatchers(ALLOW_ACCESS).permitAll());
//        return http.build();
    }

    private static void configureRoutes(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
                .requestMatchers(PUBLIC_RESOURCES).permitAll()
                .requestMatchers(ADMIN_ROUTES).hasAuthority(PerfilUsuario.ADMIN.getAuthority())
                .anyRequest().authenticated();
    }
}
