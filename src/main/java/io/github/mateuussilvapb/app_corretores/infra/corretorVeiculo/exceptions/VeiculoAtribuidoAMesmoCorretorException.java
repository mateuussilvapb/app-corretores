package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.exceptions;

public class VeiculoAtribuidoAMesmoCorretorException extends RuntimeException {
    public VeiculoAtribuidoAMesmoCorretorException(String placa, String nome) {
        super("O veículo de placa: " + placa + " já está atribuído a " + nome);
    }
}