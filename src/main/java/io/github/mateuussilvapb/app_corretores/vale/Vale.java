package io.github.mateuussilvapb.app_corretores.vale;

import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.corretor.Corretor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_vale")
public class Vale extends CreateAuditableEntity {

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "corretor_id")
    private Corretor corretor;
}
