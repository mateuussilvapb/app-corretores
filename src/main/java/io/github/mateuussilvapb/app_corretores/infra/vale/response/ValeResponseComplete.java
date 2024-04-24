package io.github.mateuussilvapb.app_corretores.infra.vale.response;

import io.github.mateuussilvapb.app_corretores.infra.corretor.Corretor;
import io.github.mateuussilvapb.app_corretores.infra.vale.Vale;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ValeResponseComplete {
    List<Vale> vales;
    Corretor corretor;
    BigDecimal total;
}
