package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VeiculoRequestUpdate {

    @Nullable
    private Vencimento vencimentoDocumento;

    @Nullable
    private Vencimento vencimentoSeguro;

    private String ufDocumento;
}