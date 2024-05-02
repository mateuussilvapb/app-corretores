package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions;

public class CorretorVeiculoNotFoundException extends RuntimeException {
    public CorretorVeiculoNotFoundException(Long id) {
        super("Correspondência para o id " + id + " não encontrada.");
    }
}