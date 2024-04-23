package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import io.github.mateuussilvapb.app_corretores.infra.veiculo.exception.VeiculoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));
    }

    public Veiculo findByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new VeiculoNotFoundException(placa));
    }

    public void deleteById(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo updateByPlaca(String placa, VeiculoRequestUpdate veiculoAtualizado) {
        var veiculo = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new VeiculoNotFoundException(placa));
        return this.updateById(veiculo.getId(), veiculoAtualizado);
    }

    public Veiculo updateById(Long id, VeiculoRequestUpdate veiculoAtualizado) {
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculo.setUfDocumento(veiculoAtualizado.getUfDocumento());
                    veiculo.setVencimentoDocumento(veiculoAtualizado.getVencimentoDocumento());
                    return veiculoRepository.save(veiculo);
                })
                .orElseThrow(() -> new VeiculoNotFoundException(id));
    }
}
