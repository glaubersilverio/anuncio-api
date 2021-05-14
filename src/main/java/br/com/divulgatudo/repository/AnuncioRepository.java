package br.com.divulgatudo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.divulgatudo.model.Anuncio;
import br.com.divulgatudo.model.Cliente;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>, JpaSpecificationExecutor<Anuncio>{

	List<Anuncio> findByCliente(Cliente cliente);
	
}
