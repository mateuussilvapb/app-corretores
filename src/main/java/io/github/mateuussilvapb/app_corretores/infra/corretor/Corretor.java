package io.github.mateuussilvapb.app_corretores.infra.corretor;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.endereco.Endereco;
import io.github.mateuussilvapb.app_corretores.infra.vale.Vale;
import io.github.mateuussilvapb.app_corretores.shared.Referable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_corretor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Corretor extends CreateAuditableEntity implements Referable<String> {

    @NotBlank
    @Size(min = 5, max = 100)
    @Column(nullable = false, unique = true)
    private String nome;

    @Size(max = 100)
    private String apelido;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, unique = true)
    private String cpf;

    @JsonManagedReference
    @JoinColumn(name = "fk_endereco_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @JsonManagedReference
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "corretor", cascade = CascadeType.ALL)
    private List<Vale> vales = new ArrayList<>();

    public boolean matchSearchTerm(String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return false;
        }
        return Objects.equals(getCpf(), searchTerm)
                || getNome().toLowerCase().contains(searchTerm.toLowerCase())
                || (getApelido() != null && getApelido().toLowerCase().contains(searchTerm.toLowerCase()));
    }

    @Override
    public String getIdentificacao() {
        return getIdString();
    }

    @Override
    public String getDescricao() {
        return getNome();
    }
}
