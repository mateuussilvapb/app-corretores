package io.github.mateuussilvapb.app_corretores.shared;

import java.io.Serializable;

public interface Referable<T> extends Serializable {

    T getIdentificacao();

    String getDescricao();

    default String referableString() {
        return "%s - %s".formatted(String.valueOf(getIdentificacao()), getDescricao());
    }
}