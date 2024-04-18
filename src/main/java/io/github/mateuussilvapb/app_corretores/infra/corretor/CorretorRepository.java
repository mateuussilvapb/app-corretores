package io.github.mateuussilvapb.app_corretores.infra.corretor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {

}
