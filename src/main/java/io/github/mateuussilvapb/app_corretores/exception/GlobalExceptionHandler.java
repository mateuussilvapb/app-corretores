package io.github.mateuussilvapb.app_corretores.exception;

import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.endereco.exceptions.EnderecoNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.vale.exceptions.ValeByCorretorIdNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.vale.exceptions.ValeNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.exception.VeiculoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CorretorNotFoundException.class)
    public void handleCorretorNotFound() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EnderecoNotFoundException.class)
    public void handleEnderecoNotFound() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VeiculoNotFoundException.class)
    public void handleVeiculoNotFound() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ValeNotFoundException.class)
    public void handleValeNotFound() {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ValeByCorretorIdNotFoundException.class)
    public void handleValeByCorretorIdNotFound() {
    }
}