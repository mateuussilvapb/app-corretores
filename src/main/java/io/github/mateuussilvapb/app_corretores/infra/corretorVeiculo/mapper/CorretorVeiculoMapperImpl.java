package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.mapper;

import io.github.mateuussilvapb.app_corretores.infra.corretor.CorretorService;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculo;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.interfaces.CorretorVeiculoMapper;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.request.CorretorVeiculoRequest;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.VeiculoService;
import org.springframework.stereotype.Service;

@Service
public class CorretorVeiculoMapperImpl implements CorretorVeiculoMapper {

    private final CorretorService corretorService;
    private final VeiculoService veiculoService;

    public CorretorVeiculoMapperImpl(CorretorService corretorService, VeiculoService veiculoService) {
        this.corretorService = corretorService;
        this.veiculoService = veiculoService;
    }

    @Override
    public CorretorVeiculo toEntity(CorretorVeiculoRequest request) {
        if (request == null) {
            return null;
        }
        var corretor = corretorService.findById(request.corretor());
        var veiculo = veiculoService.findById(request.veiculo());
        return new CorretorVeiculo(corretor, veiculo, null);
    }
}
