package io.github.mateuussilvapb.app_corretores.infra.corretor;

import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.endereco.Endereco;
import io.github.mateuussilvapb.app_corretores.infra.endereco.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorretorService {

    private final CorretorRepository corretorRepository;
    private final EnderecoRepository enderecoRepository;

    public List<Corretor> findAll() {
        return corretorRepository.findAll();
    }

    public Corretor save(Corretor corretor) {
        return corretorRepository.save(corretor);
    }

    public Corretor findById(Long id) {
        return corretorRepository.findById(id)
                .orElseThrow(() -> new CorretorNotFoundException(id));
    }

    public void deleteById(Long id) {
        corretorRepository.deleteById(id);
    }

    public Corretor update(Long id, Corretor updatedCorretor) {
        return corretorRepository.findById(id)
                .map(corretor -> {
                    corretor.setNome(updatedCorretor.getNome());
                    corretor.setDataNascimento(updatedCorretor.getDataNascimento());
                    return corretorRepository.save(corretor);
                })
                .orElseThrow(() -> new CorretorNotFoundException(id));
    }

    @Transactional
    public Endereco addEndereco(Long corretorId, Endereco endereco) {
        Corretor corretor = findById(corretorId);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        corretor.setEndereco(savedEndereco);
        corretorRepository.save(corretor);
        return savedEndereco;
    }

    @Transactional
    public void removeEndereco(Long corretorId) {
        Corretor corretor = findById(corretorId);
        corretor.setEndereco(null);
        corretorRepository.save(corretor);
    }
}