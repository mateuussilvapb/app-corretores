package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import io.github.mateuussilvapb.app_corretores.config.persistence.DomainEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_veiculo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Veiculo extends DomainEntity {

    @NotBlank
    @Size(max = 7)
    @Column(nullable = false, unique = true)
    private String placa;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "dia", column = @Column(name = "dia_vencimento_documento")),
            @AttributeOverride(name = "mes", column = @Column(name = "mes_vencimento_documento"))
    })
    private Vencimento vencimentoDocumento;

    @Nullable
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "dia", column = @Column(name = "dia_vencimento_seguro")),
            @AttributeOverride(name = "mes", column = @Column(name = "mes_vencimento_seguro"))
    })
    private Vencimento vencimentoSeguro;

    @NotNull
    @Column(nullable = false)
    private Integer anoFabricacao;

    @NotNull
    @Column(nullable = false)
    private Integer anoModelo;

    @NotBlank
    @Size(max = 2)
    private String ufDocumento;
}