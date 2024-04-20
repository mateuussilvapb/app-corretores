package io.github.mateuussilvapb.app_corretores.infra.endereco;

import io.github.mateuussilvapb.app_corretores.infra.endereco.exceptions.EnderecoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new EnderecoNotFoundException(id));
    }

    public void deleteById(Long id) {
        this.findById(id);
        enderecoRepository.deleteById(id);
    }

    public Endereco update(Long id, Endereco updatedEndereco) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setRua(updatedEndereco.getRua());
                    endereco.setNumero(updatedEndereco.getNumero());
                    endereco.setComplemento(updatedEndereco.getComplemento());
                    endereco.setBairro(updatedEndereco.getBairro());
                    endereco.setCidade(updatedEndereco.getCidade());
                    endereco.setUf(updatedEndereco.getUf());
                    endereco.setCep(updatedEndereco.getCep());
                    endereco.setReferencia(updatedEndereco.getReferencia());
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new EnderecoNotFoundException(id));
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}
