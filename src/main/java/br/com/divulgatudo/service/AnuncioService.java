package br.com.divulgatudo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.divulgatudo.dto.AnuncioDTO;
import br.com.divulgatudo.dto.AnuncioNewDTO;
import br.com.divulgatudo.model.Anuncio;
import br.com.divulgatudo.model.Cliente;
import br.com.divulgatudo.repository.AnuncioRepository;
import br.com.divulgatudo.repository.AnuncioSpecification;

@Service
public class AnuncioService {

	private AnuncioRepository anuncioRepository;
	private ModelMapper mapper;
	private ClienteService clienteService;
	
	
	
	public AnuncioService(AnuncioRepository anuncioRepository, ClienteService clienteService, ModelMapper mapper) {
		this.anuncioRepository = anuncioRepository;
		this.clienteService = clienteService;
		this.mapper = mapper;
	}
	
	public List<AnuncioDTO> findAll() {
		return this.anuncioRepository.findAll().stream().map( 
				anuncio -> mapper.map(anuncio, AnuncioDTO.class)).collect(Collectors.toList());
	}
	
	public List<AnuncioDTO> search(Long clienteId, LocalDate dataInicio, LocalDate dataTermino) {
		
		List<Anuncio> anuncios = new ArrayList<>();
		Cliente cliente = null;
		if (clienteId != null) {
			cliente = this.clienteService.findById(clienteId);
		}

		anuncios = this.anuncioRepository.findAll(AnuncioSpecification.dynamicQuerie(cliente, dataInicio, dataTermino));
		
		return anuncios.stream().map( 
				anuncio -> mapper.map(anuncio, AnuncioDTO.class)).collect(Collectors.toList());
		
	}

	public Anuncio save(AnuncioNewDTO anuncioNewDto) {
		Anuncio anuncio = mapper.map(anuncioNewDto, Anuncio.class);
		return this.anuncioRepository.save(anuncio);
	}
}
