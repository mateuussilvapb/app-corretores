package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions;

public class CorretorVeiculoByCorretorIdNotFoundException extends RuntimeException {
    public CorretorVeiculoByCorretorIdNotFoundException(Long id) {
        super("O corretor com o id " + id + " não possui veículos cadastrados.");
    }
}