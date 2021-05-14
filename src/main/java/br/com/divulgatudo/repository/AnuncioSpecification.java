package br.com.divulgatudo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.divulgatudo.model.Anuncio;
import br.com.divulgatudo.model.Cliente;


public class AnuncioSpecification {

	public static Specification<Anuncio> dynamicQuerie(Cliente cliente, LocalDate dataInicio, LocalDate dataTermino) {
		
		return new Specification<Anuncio>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if (cliente != null) {
					predicates.add(criteriaBuilder.equal(root.get("cliente"), cliente));
				}
				
				if (dataInicio != null) {
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataInicio"), dataInicio));
				}
				
				if (dataTermino != null) {
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataTermino"), dataTermino));
				}
				
				Predicate[] predicateArray = new Predicate[predicates.size()];
				return criteriaBuilder.and(predicates.toArray(predicateArray));
				
			}
		};
	}
}
