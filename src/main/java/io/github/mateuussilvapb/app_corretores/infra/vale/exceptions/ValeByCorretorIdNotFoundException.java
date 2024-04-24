package io.github.mateuussilvapb.app_corretores.infra.vale.exceptions;

public class ValeByCorretorIdNotFoundException extends RuntimeException {
    public ValeByCorretorIdNotFoundException(Long corretorID) {
        super("Não foram encontrados vales para o id do corretor " + corretorID + ".");
    }
}