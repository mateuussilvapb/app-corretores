package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo;

import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.Veiculo;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_corretor_veiculo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorretorVeiculo extends CreateAuditableEntity {
    @ManyToOne
    @JoinColumn(name = "corretor_id")
    private Corretor corretor;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Nullable
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dataDevolucao;
}
