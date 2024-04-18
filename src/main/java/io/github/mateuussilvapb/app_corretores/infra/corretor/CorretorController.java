package io.github.mateuussilvapb.app_corretores.infra.corretor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corretor")
public class CorretorController {

    private final CorretorService corretorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('employee', 'manager', 'admin')")
    public ResponseEntity<List<Corretor>> findAll() {
        List<Corretor> corretores = corretorService.findAll();
        return new ResponseEntity<>(corretores, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('employee', 'manager', 'admin')")
    public ResponseEntity<Corretor> save(@RequestBody Corretor corretor) {
        Corretor savedCorretor = corretorService.save(corretor);
        return new ResponseEntity<>(savedCorretor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('employee', 'manager', 'admin')")
    public ResponseEntity<Corretor> findById(@PathVariable Long id) {
        Corretor corretor = corretorService.findById(id);
        return new ResponseEntity<>(corretor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('manager', 'admin')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        corretorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
