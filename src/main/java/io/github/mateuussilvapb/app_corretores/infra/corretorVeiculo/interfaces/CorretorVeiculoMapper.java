package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.interfaces;


import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculo;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.request.CorretorVeiculoRequest;

public interface CorretorVeiculoMapper {

    CorretorVeiculo toEntity(CorretorVeiculoRequest request);
}
