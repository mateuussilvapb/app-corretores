package io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions;

public class CorretorComMesmoCPFException extends RuntimeException {
    public CorretorComMesmoCPFException(String cpf) {
        super("O corretor de CPF '" + cpf + "' já está cadastrado.");
    }
}