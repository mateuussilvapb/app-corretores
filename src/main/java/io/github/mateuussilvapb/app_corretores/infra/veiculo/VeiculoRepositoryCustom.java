package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import java.util.List;

public interface VeiculoRepositoryCustom {
    List<Veiculo> findAllWithFilters(VeiculoFilters filters);
}
