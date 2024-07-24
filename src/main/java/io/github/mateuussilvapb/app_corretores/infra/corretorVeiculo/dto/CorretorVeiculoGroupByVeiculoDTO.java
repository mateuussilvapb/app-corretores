package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.dto;

import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculo;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorretorVeiculoGroupByVeiculoDTO {
    private Veiculo veiculo;
    private List<CorretorVeiculoByCorretorDTO> corretores;

    public CorretorVeiculoGroupByVeiculoDTO toCorretorVeiculoGroupByVeiculoDTO(List<CorretorVeiculo> corretorVeiculos) {
        corretorVeiculos.sort(
                Comparator.comparing(CorretorVeiculo::getDataDevolucao, Comparator.nullsFirst(Comparator.naturalOrder()))
                        .thenComparing(CorretorVeiculo::getCreatedAt, Comparator.reverseOrder()));
        CorretorVeiculoGroupByVeiculoDTO dto = new CorretorVeiculoGroupByVeiculoDTO();
        dto.setVeiculo(corretorVeiculos.get(0).getVeiculo());
        dto.setCorretores(corretorVeiculos.stream().map(corretorVeiculo -> {
            CorretorVeiculoByCorretorDTO corretorDto = new CorretorVeiculoByCorretorDTO();
            corretorDto.setIdString(corretorVeiculo.getIdString());
            corretorDto.setCorretor(corretorVeiculo.getCorretor());
            corretorDto.setDataDevolucao(corretorVeiculo.getDataDevolucao());
            corretorDto.setDataAtribuicao(corretorVeiculo.getCreatedAt());
            return corretorDto;
        }).collect(Collectors.toList()));
        return dto;
    }
}
