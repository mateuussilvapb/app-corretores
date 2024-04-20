package io.github.mateuussilvapb.app_corretores.config.security;

import io.github.mateuussilvapb.app_corretores.infra.usuario.Usuario;

import java.util.Optional;

@FunctionalInterface
public interface AuthenticationResolver {

    Optional<Usuario> resolve();
}
