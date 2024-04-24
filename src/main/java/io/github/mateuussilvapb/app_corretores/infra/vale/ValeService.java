package io.github.mateuussilvapb.app_corretores.infra.vale;

import io.github.mateuussilvapb.app_corretores.infra.corretor.CorretorService;
import io.github.mateuussilvapb.app_corretores.infra.vale.exceptions.ValeByCorretorIdNotFoundException;
import io.github.mateuussilvapb.app_corretores.infra.vale.exceptions.ValeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValeService {

    private final ValeRepository valeRepository;
    private final CorretorService corretorService;

    public List<Vale> findAll() {
        return valeRepository.findAll();
    }

    public List<Vale> findByCorretorCPF(String corretorCPF) {
        Long corretorId = corretorService.findByCpf(corretorCPF).getId();
        return this.findByCorretorId(corretorId);
    }

    public List<Vale> findByCorretorId(Long corretorId) {
        return valeRepository.findByCorretorId(corretorId).orElseThrow(() -> new ValeByCorretorIdNotFoundException(corretorId));
    }

    public Vale findById(Long id) {
        return valeRepository.findById(id).orElseThrow(() -> new ValeNotFoundException(id));
    }

    public void deleteById(Long id) {
        this.findById(id);
        valeRepository.deleteById(id);
    }

    public Vale save(Vale vale) {
        return valeRepository.save(vale);
    }


}
