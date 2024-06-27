package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<List<Veiculo>> findAll() {
        List<Veiculo> veiculos = veiculoService.findAll();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    @GetMapping("/filters")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<List<Veiculo>> findByFilters(
            VeiculoFilters filters) {
        return new ResponseEntity<>(this.veiculoService.findByFilters(filters), HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Veiculo> save(@RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoService.save(veiculo);
        return new ResponseEntity<>(savedVeiculo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Veiculo> updateById(@PathVariable Long
                                                      id, @RequestBody VeiculoRequestUpdate veiculo) {
        Veiculo updatedVeiculo = veiculoService.updateById(id, veiculo);
        return new ResponseEntity<>(updatedVeiculo, HttpStatus.OK);
    }

    @PutMapping("/placa/{placa}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Veiculo> updateByPlaca(@PathVariable String placa,
                                                 @RequestBody VeiculoRequestUpdate veiculo) {
        Veiculo updatedVeiculo = veiculoService.updateByPlaca(placa, veiculo);
        return new ResponseEntity<>(updatedVeiculo, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.findById(id);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }

    @GetMapping("/placa/{placa}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Veiculo> findByPlaca(@PathVariable String placa) {
        Veiculo veiculo = veiculoService.findByPlaca(placa);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"manager", "admin"})
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        veiculoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
