package io.github.mateuussilvapb.app_corretores.infra.endereco;

import io.github.mateuussilvapb.app_corretores.infra.corretor.CorretorService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corretores/{corretorId}/enderecos")
public class EnderecoController {

    private final CorretorService corretorService;
    private final EnderecoService enderecoService;

    @GetMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Endereco> findByCorretorId(@PathVariable Long corretorId) {
        var corretor = corretorService.findById(corretorId);
        return ResponseEntity.ok(corretor.getEndereco());
    }

    @DeleteMapping
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<Void> deleteByCorretorId(@PathVariable Long corretorId) {
        var corretor = corretorService.findById(corretorId);
        var endereco = enderecoService.findById(corretor.getEndereco().getId());
        corretorService.removeEndereco(corretorId);
        enderecoService.deleteById(endereco.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Endereco> save(@PathVariable Long corretorId, @RequestBody Endereco endereco) {
        return ResponseEntity.ok(corretorService.addEndereco(corretorId, endereco));
    }

    @PutMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Endereco> update(@PathVariable Long corretorId, @RequestBody Endereco endereco) {
        var corretor = corretorService.findById(corretorId);
        return ResponseEntity.ok(enderecoService.update(corretor.getEndereco().getId(), endereco));
    }
}
