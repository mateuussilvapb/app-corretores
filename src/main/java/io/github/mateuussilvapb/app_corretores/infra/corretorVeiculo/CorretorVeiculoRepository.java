package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo;

import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorretorVeiculoRepository extends JpaRepository<CorretorVeiculo, Long> {
    Optional<List<CorretorVeiculo>> findByCorretorId(Long corretorId);

    Optional<List<CorretorVeiculo>> findByVeiculoId(Long veiculoId);

    Optional<CorretorVeiculo> findTopByVeiculoIdAndDataDevolucaoIsNullOrderByIdDesc(Long veiculoId);

    Optional<CorretorVeiculo> findByCorretorAndVeiculoAndDataDevolucaoIsNull(Corretor corretor, Veiculo veiculo);
}
