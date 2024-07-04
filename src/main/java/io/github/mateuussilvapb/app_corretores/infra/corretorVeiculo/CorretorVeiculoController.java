package io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo;

import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.dto.CorretorVeiculoByCorretorIDResponse;
import io.github.mateuussilvapb.app_corretores.infra.corretorVeiculo.request.CorretorVeiculoRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/corretores-veiculos")
public class CorretorVeiculoController {

    private final CorretorVeiculoService corretorVeiculoService;

    @PostMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<CorretorVeiculo> create(@RequestBody CorretorVeiculoRequest corretorVeiculoRequest) {
        return ResponseEntity.ok(corretorVeiculoService.save(corretorVeiculoRequest));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<CorretorVeiculo> get(@PathVariable Long id) {
        return ResponseEntity.ok(corretorVeiculoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        corretorVeiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<List<CorretorVeiculo>> getAll() {
        return ResponseEntity.ok(corretorVeiculoService.getAll());
    }

    @GetMapping("/historico/corretor/{corretorId}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<CorretorVeiculoByCorretorIDResponse> getHistoricoByCorretorId(@PathVariable Long corretorId) {
        var corretorVeiculo = corretorVeiculoService.findHistoricoByCorretorId(corretorId);
        return ResponseEntity.ok(new CorretorVeiculoByCorretorIDResponse(corretorVeiculo));
    }

    @GetMapping("/historico/veiculo/{veiculoId}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<List<CorretorVeiculo>> getHistoricoByVeiculoId(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(corretorVeiculoService.findHistoricoByVeiculoId(veiculoId));
    }

    @GetMapping("/veiculo/{veiculoId}")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<CorretorVeiculo> getCorretorVeiculoByVeiculoId(@PathVariable Long veiculoId) {
        return corretorVeiculoService
                .findTopByVeiculoIdAndDataDevolucaoIsNullOrderByIdDesc(veiculoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}/devolucao")
    @RolesAllowed({"employee", "manager", "admin"})
    public ResponseEntity<CorretorVeiculo> updateDataDevolucao(@PathVariable Long id,
                                                               @RequestParam(required = false) LocalDateTime dataDevolucao) {
        return ResponseEntity.ok(corretorVeiculoService.updateDataDevolucao(id,
                dataDevolucao == null ? LocalDateTime.now() : dataDevolucao));
    }
}
