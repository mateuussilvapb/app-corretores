package io.github.mateuussilvapb.app_corretores.exception;

import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions.*;
import io.github.mateuussilvapb.app_corretores.infra.endereco.exceptions.EnderecoNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.vale.exceptions.ValeByCorretorIdNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.vale.exceptions.ValeNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.veiculo.exception.VeiculoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CorretorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCorretorNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEnderecoNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVeiculoNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ValeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValeNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ValeByCorretorIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValeByCorretorIdNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CorretorVeiculoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCorretorVeiculoNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CorretorVeiculoByVeiculoIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCorretorVeiculoByVeiculoIdNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CorretorVeiculoByCorretorIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCorretorVeiculoByCorretorIdNotFound(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VeiculoAtribuidoAMesmoCorretorException.class)
    public ResponseEntity<ErrorResponse> handleVeiculoAtribuidoAMesmoCorretor(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VeiculoAtribuidoOutroCorretorException.class)
    public ResponseEntity<ErrorResponse> handleVeiculoAtribuidoOutroCorretor(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getClass().getName(),
                        ex.getMessage(),
                        LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    public record ErrorResponse(HttpStatus status, String error, String message,
                                LocalDateTime timestamp,
                                Map<String, String> custom) {
        public ErrorResponse(HttpStatus status, String error, String message,
                             LocalDateTime timestamp) {
            this(status, error, message, timestamp, Collections.emptyMap());
        }
    }
}