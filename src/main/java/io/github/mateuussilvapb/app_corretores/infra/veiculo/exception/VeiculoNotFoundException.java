package io.github.mateuussilvapb.app_corretores.infra.veiculo.exception;

public class VeiculoNotFoundException extends RuntimeException {
    public VeiculoNotFoundException(Long id) {
        super("Veículo com o id " + id + " não encontrado.");
    }

    public VeiculoNotFoundException(String placa) {
        super("Veículo com a placa " + placa + " não encontrado.");
    }

}