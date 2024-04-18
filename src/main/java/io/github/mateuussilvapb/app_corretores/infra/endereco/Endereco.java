package io.github.mateuussilvapb.app_corretores.infra.endereco;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_endereco")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Endereco extends CreateAuditableEntity {
    @NotBlank
    @Size(max = 9)
    private String cep;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    @Size(max = 2)
    private String uf;

    private String complemento;

    @NotBlank
    private String numero;

    private String referencia;

    private String observacao;

    @JsonBackReference
    @OneToOne(mappedBy = "endereco")
    private Corretor corretor;
}
