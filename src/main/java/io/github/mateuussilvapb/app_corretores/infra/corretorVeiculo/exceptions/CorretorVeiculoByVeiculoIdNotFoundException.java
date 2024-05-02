package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions;

public class CorretorVeiculoByVeiculoIdNotFoundException extends RuntimeException {
    public CorretorVeiculoByVeiculoIdNotFoundException(Long id) {
        super("O veiculo com o id " + id + " não possui corretores vinculados.");
    }
}