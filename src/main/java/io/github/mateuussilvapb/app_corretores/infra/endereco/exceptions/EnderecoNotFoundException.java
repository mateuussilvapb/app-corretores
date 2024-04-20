package io.github.mateuussilvapb.app_corretores.infra.endereco.exceptions;

public class EnderecoNotFoundException extends RuntimeException {
    public EnderecoNotFoundException(Long id) {
        super("Endereço com o id " + id + " não encontrado.");
    }
}