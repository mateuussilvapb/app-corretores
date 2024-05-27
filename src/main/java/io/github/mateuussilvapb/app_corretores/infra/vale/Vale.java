package io.github.mateuussilvapb.app_corretores.infra.vale;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_vale")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vale extends CreateAuditableEntity {

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal valor;

    @Size(max = 255)
    private String descricao;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "corretor_id")
    private Corretor corretor;
}
