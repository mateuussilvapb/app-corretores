package io.github.mateuussilvapb.app_corretores.infra.veiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class VeiculoRepositoryCustomImpl implements VeiculoRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Veiculo> findAllWithFilters(VeiculoFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Veiculo> cq = cb.createQuery(Veiculo.class);
        Root<Veiculo> veiculo = cq.from(Veiculo.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filters.getPlaca().isPresent()) {
            predicates.add(cb.equal(cb.lower(veiculo.get("placa")), filters.getPlaca().get().toLowerCase()));
        }
        if (filters.getModelo().isPresent()) {
            predicates.add(cb.equal(cb.lower(veiculo.get("modelo")), filters.getModelo().get().toLowerCase()));
        }
        if (filters.getMarca().isPresent()) {
            predicates.add(cb.equal(cb.lower(veiculo.get("marca")),
                    filters.getMarca().get().toLowerCase()));
        }
        if (filters.getAnoFabricacao().isPresent()) {
            predicates.add(cb.equal(veiculo.get("anoFabricacao"),
                    filters.getAnoFabricacao().get()));
        }
        if (filters.getAnoModelo().isPresent()) {
            predicates.add(cb.equal(veiculo.get("anoModelo"),
                    filters.getAnoModelo().get()));
        }
        if (filters.getUfDocumento().isPresent()) {
            predicates.add(cb.equal(cb.lower(veiculo.get("ufDocumento")),
                    filters.getUfDocumento().get().toLowerCase()));
        }
        if (filters.getVencimentoDocumento().isPresent()) {
            predicates.add(cb.equal(veiculo.get("vencimentoDocumento"), filters.getVencimentoDocumento().get()));
        }
        if (filters.getVencimentoSeguro().isPresent()) {
            predicates.add(cb.equal(veiculo.get("vencimentoSeguro"), filters.getVencimentoSeguro().get()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
