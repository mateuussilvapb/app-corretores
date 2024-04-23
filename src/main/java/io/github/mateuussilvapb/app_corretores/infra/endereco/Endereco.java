package io.github.mateuussilvapb.app_corretores.infra.endereco;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.DomainEntity;
import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import jakarta.persistence.CascadeType;
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
public class Endereco extends DomainEntity {
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

    @NotBlank
    private String rua;

    private String complemento;

    @NotBlank
    private String numero;

    private String referencia;

    @JsonBackReference
    @OneToOne(mappedBy = "endereco", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    private Corretor corretor;
}
