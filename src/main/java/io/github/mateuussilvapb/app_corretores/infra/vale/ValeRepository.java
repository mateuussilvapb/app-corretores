package io.github.mateuussilvapb.app_corretores.infra.vale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValeRepository extends JpaRepository<Vale, Long> {
    Optional<List<Vale>> findByCorretorId(Long corretorId);
}
