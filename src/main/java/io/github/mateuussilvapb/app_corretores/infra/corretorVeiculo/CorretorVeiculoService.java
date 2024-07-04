package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo;

import io.github.mateuussilvapb.app_corretores.infra.corretor.CorretorService;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions.CorretorVeiculoByCorretorIdNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions.CorretorVeiculoByVeiculoIdNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions.CorretorVeiculoNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.interfaces.CorretorVeiculoMapper;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.request.CorretorVeiculoRequest;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.validations.CorretorVeiculoValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorretorVeiculoService {

    private final CorretorService corretorService;
    private final CorretorVeiculoRepository corretorVeiculoRepository;
    private final CorretorVeiculoMapper corretorVeiculoMapper;
    private final CorretorVeiculoValidate corretorVeiculoValidate;

    public CorretorVeiculo save(CorretorVeiculoRequest corretorVeiculo) {
        CorretorVeiculo corretorEntity = corretorVeiculoMapper.toEntity(corretorVeiculo);
        this.corretorVeiculoValidate.validateVeiculoAtribuidoAMesmoCorretor(corretorEntity);
        this.corretorVeiculoValidate.validateVeiculoAtribuidoOutroCorretor(corretorEntity);
        return corretorVeiculoRepository.save(corretorEntity);
    }

    public CorretorVeiculo findById(Long id) {
        return corretorVeiculoRepository.findById(id).orElseThrow(() -> new CorretorVeiculoNotFoundException(id));
    }

    public void deleteById(Long id) {
        this.findById(id);
        corretorVeiculoRepository.deleteById(id);
    }

    public List<CorretorVeiculo> getAll() {
        return corretorVeiculoRepository.findAll();
    }

    public List<CorretorVeiculo> findHistoricoByCorretorId(Long corretorId) {
        this.corretorService.findById(corretorId);
        return corretorVeiculoRepository.findByCorretorId(corretorId).orElseThrow(() -> new CorretorVeiculoByCorretorIdNotFoundException(corretorId));
    }

    public List<CorretorVeiculo> findHistoricoByVeiculoId(Long veiculoId) {
        return corretorVeiculoRepository.findByVeiculoId(veiculoId).orElseThrow(() -> new CorretorVeiculoByVeiculoIdNotFoundException(veiculoId));
    }

    public Optional<CorretorVeiculo> findTopByVeiculoIdAndDataDevolucaoIsNullOrderByIdDesc(Long veiculoId) {
        return corretorVeiculoRepository.findTopByVeiculoIdAndDataDevolucaoIsNullOrderByIdDesc(veiculoId);
    }

    public CorretorVeiculo updateDataDevolucao(Long id, LocalDateTime dataDevolucao) {
        CorretorVeiculo corretorVeiculo = this.findById(id);
        corretorVeiculo.setDataDevolucao(dataDevolucao);
        return corretorVeiculoRepository.save(corretorVeiculo);
    }
}
