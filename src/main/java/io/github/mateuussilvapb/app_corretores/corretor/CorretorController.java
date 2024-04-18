package io.github.mateuussilvapb.app_corretores.corretor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corretor")
public class CorretorController {

    @GetMapping
    @PreAuthorize("hasAnyRole('employee', 'manager', 'admin')")
    public String list() {
        return "Listando corretores...";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('admin', 'manager')")
    public String create() {
        return "Cadastrando corretor...";
    }
}
