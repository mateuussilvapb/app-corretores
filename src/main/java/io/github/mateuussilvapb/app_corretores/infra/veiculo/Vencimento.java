package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Vencimento {

    @Min(1)
    @Max(31)
    private int dia;

    @Min(1)
    @Max(12)
    private int mes;
}