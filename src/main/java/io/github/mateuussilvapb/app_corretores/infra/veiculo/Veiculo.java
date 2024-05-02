package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import io.github.mateuussilvapb.app_corretores.config.persistence.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Vencimento vencimentoDocumento;

    @NotNull
    @Column(nullable = false)
    private String anoFabricacao;

    @NotNull
    @Column(nullable = false)
    private String anoModelo;

    @NotBlank
    @Size(max = 2)
    private String ufDocumento;
}