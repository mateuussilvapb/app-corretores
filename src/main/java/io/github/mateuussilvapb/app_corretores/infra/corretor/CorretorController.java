package io.github.mateuussilvapb.app_corretores.infra.corretor;

import io.github.mateuussilvapb.app_corretores.shared.Reference;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corretores")
public class CorretorController {

    private final CorretorService corretorService;

    @GetMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<List<Corretor>> findAll() {
        List<Corretor> corretores = corretorService.findAll();
        return new ResponseEntity<>(corretores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Corretor> findById(@PathVariable Long id) {
        Corretor corretor = corretorService.findById(id);
        return new ResponseEntity<>(corretor, HttpStatus.OK);
    }

    @GetMapping("/cpf/{cpf}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Corretor> findByCpf(@RequestParam String cpf) {
        Corretor corretor = corretorService.findByCpf(cpf);
        return new ResponseEntity<>(corretor, HttpStatus.OK);
    }

    @GetMapping("/search")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<List<Reference<String>>> findCorretor(@RequestParam(name = "term",
            required =
                    false) String searchTerm) {
        return new ResponseEntity<>(Reference.valueOf(corretorService.findCorretor(searchTerm)),
                HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Corretor> save(@RequestBody Corretor corretor) {
        Corretor savedCorretor = corretorService.save(corretor);
        return new ResponseEntity<>(savedCorretor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        corretorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
