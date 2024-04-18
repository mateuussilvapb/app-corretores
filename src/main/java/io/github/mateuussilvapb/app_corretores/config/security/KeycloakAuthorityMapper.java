package io.github.mateuussilvapb.app_corretores.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class KeycloakAuthorityMapper implements GrantedAuthoritiesMapper {

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return Stream.concat(authorities.stream(), roles(authorities).stream().map(SimpleGrantedAuthority::new))
                .collect(Collectors.toList());
    }

    private static Set<String> roles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(KeycloakAuthorityMapper::roles)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private static List<String> roles(GrantedAuthority authority) {
        if (authority instanceof OidcUserAuthority oidcUserAuthority) {
            return ClaimsRolesExtractor.extract(oidcUserAuthority.getUserInfo().getClaims());
        }
        return Collections.emptyList();
    }
}
