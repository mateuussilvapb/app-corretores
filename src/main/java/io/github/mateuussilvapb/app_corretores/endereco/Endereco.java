package io.github.mateuussilvapb.app_corretores.endereco;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.corretor.Corretor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_endereco")
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
