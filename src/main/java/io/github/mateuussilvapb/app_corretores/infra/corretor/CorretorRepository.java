package io.github.mateuussilvapb.app_corretores.infra.corretor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {
    Optional<Corretor> findByCpf(String cpf);
}
