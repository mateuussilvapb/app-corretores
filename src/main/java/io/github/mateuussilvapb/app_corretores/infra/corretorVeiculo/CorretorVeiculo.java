package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo;

import io.github.mateuussilvapb.app_corretores.config.persistence.CreateAuditableEntity;
import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_corretor_veiculo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorretorVeiculo extends CreateAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "corretor_id")
    private Corretor corretor;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
}
