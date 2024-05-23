package io.github.mateuussilvapb.app_corretores.infra.corretor;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {
    Optional<Corretor> findByCpf(String cpf);

    @Override
    @Cacheable("findAllCorretores")
    @SuppressWarnings("NullableProblems")
    List<Corretor> findAll();

    Optional<Object> findByNome(String nome);
}
