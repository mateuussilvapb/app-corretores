package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.dto;

import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorretorVeiculoByCorretorDTO {
    private String idString;
    private Corretor corretor;
    private LocalDateTime dataDevolucao;
    private LocalDateTime dataAtribuicao;
}
