package io.github.mateuussilvapb.app_corretores.infra.corretor.validations;

import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.corretor.CorretorRepository;
import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorComMesmoCPFException;
import io.github.mateuussilvapb.app_corretores.infra.corretor.exceptions.CorretorComMesmoNomeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorretorValidate {
    private final CorretorRepository corretorRepository;

    public void validateCorretorComMesmoNome(Corretor corretor) {
        corretorRepository.findByNome(corretor.getNome())
                .ifPresent(existingCorretor -> {
                    throw new CorretorComMesmoNomeException(corretor.getNome());
                });
    }

    public void validateCorretorComMesmoCPF(Corretor corretor) {
        corretorRepository.findByCpf(corretor.getCpf())
                .ifPresent(existingCorretor -> {
                    throw new CorretorComMesmoCPFException(corretor.getCpf());
                });
    }

}
