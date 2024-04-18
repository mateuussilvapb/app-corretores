package io.github.mateuussilvapb.app_corretores.infra.corretor;

import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorretorService {

    private final CorretorRepository corretorRepository;

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
}