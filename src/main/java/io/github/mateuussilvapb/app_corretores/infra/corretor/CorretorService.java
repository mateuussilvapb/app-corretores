package io.github.mateuussilvapb.app_corretores.infra.corretor;

import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.corretor.validations.CorretorValidate;
import io.github.mateuussilvapb.app_corretores.infra.endereco.Endereco;
import io.github.mateuussilvapb.app_corretores.infra.endereco.EnderecoRepository;
import io.github.mateuussilvapb.app_corretores.infra.vale.Vale;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorretorService {

    private final CorretorValidate corretorValidate;
    private final CorretorRepository corretorRepository;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public List<Corretor> findAll() {
        return corretorRepository.findAll();
    }

    @Transactional
    public List<Corretor> findCorretor(String searchTerm) {
        if (StringUtils.isBlank(searchTerm)) {
            return corretorRepository.findAll();
        }
        return findAll().stream().filter(corretor -> corretor.matchSearchTerm(searchTerm)).toList();
    }

    @Transactional
    public Corretor findByCpf(String cpf) {
        return corretorRepository.findByCpf(cpf).orElseThrow(() -> new CorretorNotFoundException(cpf));
    }

    @Transactional
    @CacheEvict(value = "findAllCorretores", allEntries = true)
    public Corretor save(Corretor corretor) {
        this.corretorValidate.validateCorretorComMesmoNome(corretor);
        this.corretorValidate.validateCorretorComMesmoCPF(corretor);
        return corretorRepository.save(corretor);
    }

    @Transactional
    public Corretor findById(Long id) {
        return corretorRepository.findById(id)
                .orElseThrow(() -> new CorretorNotFoundException(id));
    }

    @Transactional
    @CacheEvict(value = "findAllCorretores", allEntries = true)
    public void deleteById(Long id) {
        corretorRepository.deleteById(id);
    }

    @Transactional
    @CacheEvict(value = "findAllCorretores", allEntries = true)
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
    @CacheEvict(value = "findAllCorretores", allEntries = true)
    public Endereco addEndereco(Long corretorId, Endereco endereco) {
        Corretor corretor = findById(corretorId);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        corretor.setEndereco(savedEndereco);
        corretorRepository.save(corretor);
        return savedEndereco;
    }

    @Transactional
    @CacheEvict(value = "findAllCorretores", allEntries = true)
    public void removeEndereco(Long corretorId) {
        Corretor corretor = findById(corretorId);
        corretor.setEndereco(null);
        corretorRepository.save(corretor);
    }

    @Transactional
    @CacheEvict(value = "findAllCorretores", allEntries = true)
    public Vale addVale(Long corretorId, Vale vale) {
        Corretor corretor = findById(corretorId);
        vale.setCorretor(corretor);
        corretor.getVales().add(vale);
        corretorRepository.save(corretor);
        return vale;
    }

    @Transactional
    @CacheEvict(value = "findAllCorretores", allEntries = true)
    public void removeVale(Long corretorId, Long valeId) {
        Corretor corretor = findById(corretorId);
        Vale vale = corretor.getVales().stream()
                .filter(v -> v.getId().equals(valeId))
                .findFirst()
                .orElseThrow(() -> new CorretorNotFoundException(corretorId));
        corretor.getVales().remove(vale);
        corretorRepository.save(corretor);
    }
}