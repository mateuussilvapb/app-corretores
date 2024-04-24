package io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions;

public class CorretorNotFoundException extends RuntimeException {
    public CorretorNotFoundException(Long id) {
        super("Corretor com o id " + id + " não encontrado.");
    }

    public CorretorNotFoundException(String cpf) {
        super("Corretor com o CPF " + cpf + " não encontrado.");
    }
}