package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.dto;

import io.github.mateuussilvapb.app_corretores.infra.veiculo.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorretorVeiculoDTO {
    private String idString;
    private Veiculo veiculo;
    private LocalDateTime dataDevolucao;
}