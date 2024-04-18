package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_veiculo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Veiculo extends CreateAuditableEntity {

    @NotBlank
    @Size(max = 7)
    private String placa;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;
}