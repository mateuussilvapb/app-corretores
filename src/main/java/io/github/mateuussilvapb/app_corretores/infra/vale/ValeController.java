package io.github.mateuussilvapb.app_corretores.infra.vale;

import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.corretor.CorretorService;
import io.github.mateuussilvapb.app_corretores.infra.vale.response.ValeResponseComplete;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vales")
public class ValeController {

    private final ValeService valeService;
    private final CorretorService corretorService;

    @GetMapping
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<List<Vale>> findAll() {
        List<Vale> vales = valeService.findAll();
        return new ResponseEntity<>(vales, HttpStatus.OK);
    }

    @GetMapping("/corretor/cpf/{corretorCPF}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<ValeResponseComplete> findByCorretorCPF(@PathVariable String corretorCPF) {
        List<Vale> valesByCorretorCPF = valeService.findByCorretorCPF(corretorCPF);
        Corretor corretor = corretorService.findByCpf(corretorCPF);
        ValeResponseComplete valeResponseComplete = new ValeResponseComplete();
        valeResponseComplete.setVales(valesByCorretorCPF);
        valeResponseComplete.setCorretor(corretor);
        valeResponseComplete.setTotal(valesByCorretorCPF.stream()
                .map(Vale::getValor)
                .reduce(BigDecimal::add)
                .orElse(null));
        return new ResponseEntity<>(valeResponseComplete, HttpStatus.OK);
    }

    @GetMapping("/corretor/{corretorId}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<ValeResponseComplete> findByCorretorId(@PathVariable Long corretorId) {
        List<Vale> valesByCorretorCPF = valeService.findByCorretorId(corretorId);
        Corretor corretor = corretorService.findById(corretorId);
        ValeResponseComplete valeResponseComplete = new ValeResponseComplete();
        valeResponseComplete.setVales(valesByCorretorCPF);
        valeResponseComplete.setCorretor(corretor);
        valeResponseComplete.setTotal(valesByCorretorCPF.stream()
                .map(Vale::getValor)
                .reduce(BigDecimal::add)
                .orElse(null));
        return new ResponseEntity<>(valeResponseComplete, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Vale> findById(@PathVariable Long id) {
        Vale vale = valeService.findById(id);
        return new ResponseEntity<>(vale, HttpStatus.OK);
    }

    @DeleteMapping("/corretor/{idCorretor}/vale/{id}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<Void> deleteByIdAndCorretorId(@PathVariable Long idCorretor, @PathVariable Long id) {
        corretorService.removeVale(idCorretor, id);
        valeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/corretor/{corretorId}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<Vale> save(@PathVariable Long corretorId, @RequestBody Vale vale) {
        return ResponseEntity.ok(corretorService.addVale(corretorId, vale));
    }
}
