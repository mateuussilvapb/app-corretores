package io.github.mateuussilvapb.app_corretores.infra.corretor;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.endereco.Endereco;
import io.github.mateuussilvapb.app_corretores.infra.vale.Vale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_corretor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Corretor extends CreateAuditableEntity {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, unique = true)
    private String cpf;

    @JsonManagedReference
    @JoinColumn(name = "fk_endereco_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @JsonManagedReference
    @OneToMany(mappedBy = "corretor", cascade = CascadeType.ALL)
    private List<Vale> vales = new ArrayList<>();
}
