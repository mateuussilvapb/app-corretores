package io.github.mateuussilvapb.app_corretores.config.persistence;

import io.github.mateuussilvapb.app_corretores.config.security.AuthenticationResolverFacade;
import io.github.mateuussilvapb.app_corretores.infra.usuario.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {

    private static final String ANONYMOUS = "anonymousUser";

    @Bean
    AuditorAware<String> auditorProvider() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        return () -> AuthenticationResolverFacade.resolve().map(Usuario::login);
    }
}
