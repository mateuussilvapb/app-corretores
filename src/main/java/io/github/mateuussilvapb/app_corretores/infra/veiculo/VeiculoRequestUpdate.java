package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VeiculoRequestUpdate {

    private Vencimento vencimentoDocumento;
    private String ufDocumento;
}