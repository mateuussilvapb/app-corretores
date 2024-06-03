package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.dto;

import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorretorVeiculoByCorretorIDResponse {
    private Corretor corretor;
    private List<CorretorVeiculoDTO> historico;

    public CorretorVeiculoByCorretorIDResponse(List<CorretorVeiculo> corretorVeiculos) {
        this.corretor = corretorVeiculos.get(0).getCorretor();
        this.historico =
                corretorVeiculos.stream()
                        .map(
                                corretorVeiculo ->
                                        new CorretorVeiculoDTO(
                                                corretorVeiculo.getIdString(),
                                                corretorVeiculo.getVeiculo(),
                                                corretorVeiculo.getDataDevolucao(),
                                                corretorVeiculo.getCreatedAt()))
                        .collect(Collectors.toList());
    }
}
