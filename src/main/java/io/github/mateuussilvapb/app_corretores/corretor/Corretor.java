package io.github.mateuussilvapb.app_corretores.corretor;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.endereco.Endereco;
import io.github.mateuussilvapb.app_corretores.vale.Vale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_corretor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Corretor extends CreateAuditableEntity {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private Date dataNascimento;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "fk_endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "corretor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vale> vales = new ArrayList<>();
}
