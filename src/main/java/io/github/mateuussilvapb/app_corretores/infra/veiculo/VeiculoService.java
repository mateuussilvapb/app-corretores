package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculo;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.CorretorVeiculoRepository;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.exception.VeiculoNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final CorretorVeiculoRepository corretorVeiculoRepository;

    public List<Veiculo> findAll() {
        List<Veiculo> veiculos = veiculoRepository.findAll();

        veiculos.sort(Comparator.comparing(veiculo -> {
            Vencimento vencimentoDocumento = veiculo.getVencimentoDocumento();
            Vencimento vencimentoSeguro = veiculo.getVencimentoSeguro();

            LocalDate dataVencimentoDocumento = LocalDate.of(LocalDate.now().getYear(), Month.of(vencimentoDocumento.getMes()), vencimentoDocumento.getDia());
            LocalDate dataVencimentoSeguro;

            if (vencimentoSeguro != null && vencimentoSeguro.getMes() != 0 && vencimentoSeguro.getDia() != 0) {
                dataVencimentoSeguro = LocalDate.of(LocalDate.now().getYear(), Month.of(vencimentoSeguro.getMes()), vencimentoSeguro.getDia());
            } else {
                dataVencimentoSeguro = LocalDate.of(9999, 12, 31);
            }

            if (dataVencimentoDocumento.isBefore(LocalDate.now())) {
                dataVencimentoDocumento = dataVencimentoDocumento.plusYears(1);
            }

            if (dataVencimentoSeguro.isBefore(LocalDate.now())) {
                dataVencimentoSeguro = dataVencimentoSeguro.plusYears(1);
            }

            return dataVencimentoDocumento.isBefore(dataVencimentoSeguro) ? dataVencimentoDocumento : dataVencimentoSeguro;
        }));

        return veiculos;
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

    @Transactional
    public void deleteById(Long id) {
        Optional<List<CorretorVeiculo>> corretoresVeiculos = corretorVeiculoRepository.findByVeiculoId(id);
        corretoresVeiculos.ifPresent(corretorVeiculoRepository::deleteAll);
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
                    veiculo.setVencimentoSeguro(veiculoAtualizado.getVencimentoSeguro());
                    return veiculoRepository.save(veiculo);
                })
                .orElseThrow(() -> new VeiculoNotFoundException(id));
    }

    public List<Veiculo> findByFilters(VeiculoFilters filters) {
        List<Veiculo> veiculos = veiculoRepository.findAllWithFilters(filters);

        veiculos.sort(Comparator.comparing(veiculo -> {
            Vencimento vencimentoDocumento = veiculo.getVencimentoDocumento();
            Vencimento vencimentoSeguro = veiculo.getVencimentoSeguro();

            LocalDate dataVencimentoDocumento = LocalDate.of(LocalDate.now().getYear(), Month.of(vencimentoDocumento.getMes()), vencimentoDocumento.getDia());
            LocalDate dataVencimentoSeguro;

            if (vencimentoSeguro != null && vencimentoSeguro.getMes() != 0 && vencimentoSeguro.getDia() != 0) {
                dataVencimentoSeguro = LocalDate.of(LocalDate.now().getYear(), Month.of(vencimentoSeguro.getMes()), vencimentoSeguro.getDia());
            } else {
                dataVencimentoSeguro = LocalDate.of(9999, 12, 31);
            }

            if (dataVencimentoDocumento.isBefore(LocalDate.now())) {
                dataVencimentoDocumento = dataVencimentoDocumento.plusYears(1);
            }

            if (dataVencimentoSeguro.isBefore(LocalDate.now())) {
                dataVencimentoSeguro = dataVencimentoSeguro.plusYears(1);
            }

            return dataVencimentoDocumento.isBefore(dataVencimentoSeguro) ? dataVencimentoDocumento : dataVencimentoSeguro;
        }));

        return veiculos;
    }
}
