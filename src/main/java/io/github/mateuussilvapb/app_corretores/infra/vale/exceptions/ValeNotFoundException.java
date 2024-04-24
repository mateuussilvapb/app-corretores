package io.github.mateuussilvapb.app_corretores.infra.vale.exceptions;

public class ValeNotFoundException extends RuntimeException {
    public ValeNotFoundException(Long id) {
        super("Vale com o id " + id + " não encontrado.");
    }
}