package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.validations;

import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculo;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculoRepository;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions.VeiculoAtribuidoAMesmoCorretorException;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions.VeiculoAtribuidoOutroCorretorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorretorVeiculoValidate {

    private final CorretorVeiculoRepository corretorVeiculoRepository;

    public void validateVeiculoAtribuidoAMesmoCorretor(CorretorVeiculo corretorVeiculo) {
        corretorVeiculoRepository.findByCorretorAndVeiculoAndDataDevolucaoIsNull(
                        corretorVeiculo.getCorretor(), corretorVeiculo.getVeiculo())
                .ifPresent(existingCorretorVeiculo -> {
                    throw new VeiculoAtribuidoAMesmoCorretorException(corretorVeiculo.getVeiculo().getPlaca(), corretorVeiculo.getCorretor().getNome());
                });
    }

    public void validateVeiculoAtribuidoOutroCorretor(CorretorVeiculo corretorVeiculo) {
        corretorVeiculoRepository.findTopByVeiculoIdAndDataDevolucaoIsNullOrderByIdDesc(
                        corretorVeiculo.getVeiculo().getId())
                .ifPresent(existingCorretorVeiculo -> {
                    if (!existingCorretorVeiculo.getCorretor().equals(corretorVeiculo.getCorretor())) {
                        throw new VeiculoAtribuidoOutroCorretorException(corretorVeiculo.getVeiculo().getPlaca(), existingCorretorVeiculo.getCorretor().getNome());
                    }
                });
    }

}