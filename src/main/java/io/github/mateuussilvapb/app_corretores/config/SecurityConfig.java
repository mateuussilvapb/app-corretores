package io.github.mateuussilvapb.app_corretores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeConfig -> {
                    // Autoriaza o acesso a todas as rotas que possuem em seu prefixo a uri public
                    authorizeConfig.requestMatchers("/public").permitAll();
                    // Autoriaza o acesso a todas as rotas que possuem em seu prefixo a uri logout
                    authorizeConfig.requestMatchers("/logout").permitAll();
                    // Permitir acesso ao console do H2
                    authorizeConfig.requestMatchers("/h2-console/**").permitAll();
                    // Solicita que para todas as demais rotas, o acesso esteja autenticado
                    authorizeConfig.anyRequest().authenticated();
                })
                // Desabilitar CSRF para o console do H2
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                // Permite o acesso ao formulário de Login (próprio do Spring Security) com as constumizações padrões.
                .formLogin(Customizer.withDefaults()).build();
    }
}
