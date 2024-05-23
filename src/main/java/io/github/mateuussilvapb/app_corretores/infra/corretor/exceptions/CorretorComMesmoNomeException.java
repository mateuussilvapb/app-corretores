package io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions;

public class CorretorComMesmoNomeException extends RuntimeException {
    public CorretorComMesmoNomeException(String nome) {
        super("O corretor de nome '" + nome + "' já está cadastrado.");
    }
}