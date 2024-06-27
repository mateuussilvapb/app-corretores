package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryCustom {
    Optional<Veiculo> findByPlaca(String placa);
}
