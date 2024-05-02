package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions;

public class VeiculoAtribuidoOutroCorretorException extends RuntimeException {
    public VeiculoAtribuidoOutroCorretorException(String placa, String nome) {
        super("O veículo de placa: " + placa + " está atribuído ao corretor " + nome);
    }
}