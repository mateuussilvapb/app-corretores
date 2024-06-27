package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Builder
public record VeiculoFilters(
        String placa,
        String modelo,
        String marca,
        Integer anoFabricacao,
        Integer anoModelo,
        String ufDocumento,
        Vencimento vencimentoDocumento,
        Vencimento vencimentoSeguro
) {
    public Optional<String> getPlaca() {
        return Optional.ofNullable(placa).filter(StringUtils::isNotBlank);
    }

    public Optional<String> getModelo() {
        return Optional.ofNullable(modelo).filter(StringUtils::isNotBlank);
    }

    public Optional<String> getMarca() {
        return Optional.ofNullable(marca).filter(StringUtils::isNotBlank);
    }

    public Optional<Integer> getAnoFabricacao() {
        return Optional.ofNullable(anoFabricacao);
    }

    public Optional<Integer> getAnoModelo() {
        return Optional.ofNullable(anoModelo);
    }

    public Optional<String> getUfDocumento() {
        return Optional.ofNullable(ufDocumento).filter(StringUtils::isNotBlank);
    }

    public Optional<Vencimento> getVencimentoDocumento() {
        return Optional.ofNullable(vencimentoDocumento);
    }

    public Optional<Vencimento> getVencimentoSeguro() {
        return Optional.ofNullable(vencimentoSeguro);
    }
}
