package io.github.mateuussilvapb.app_corretores.infra.vale;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "corretor_id")
    private Corretor corretor;
}
