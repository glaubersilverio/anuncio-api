package br.com.divulgatudo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.divulgatudo.dto.ClienteDTO;
import br.com.divulgatudo.dto.ClienteNewDTO;
import br.com.divulgatudo.exception.NotFoundException;
import br.com.divulgatudo.model.Cliente;
import br.com.divulgatudo.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	
	private ModelMapper mapper;
	
	public ClienteService(ClienteRepository clienteRepository, ModelMapper mapper) {
		this.clienteRepository = clienteRepository;
		this.mapper = mapper;
	}
	
	public Cliente findById(Long id) {
		return this.clienteRepository.findById(id)
				.orElseThrow( () -> new NotFoundException("Cliente not found with id " + id) );
	}
	
	public Cliente findByNome(String nome) {
		return this.clienteRepository.findByNome(nome)
				.orElseThrow( () -> new NotFoundException("Cliente not found with nome " + nome));
	}
	
	public void delete(Cliente cliente) {
		if (this.clienteRepository.existsById(cliente.getId())) {
			this.clienteRepository.delete(cliente);
		} else {
			throw new IllegalArgumentException("Cliente not found with id " + cliente.getId());
		}
	}
	
	public Cliente update(ClienteDTO clienteDto) {
		Cliente cliente = mapper.map(clienteDto, Cliente.class);
		Cliente clienteSaved = this.findById(cliente.getId());
		if (clienteSaved.getId().equals(cliente.getId()) && clienteSaved.getNome().equals(cliente.getNome())) {
			throw new IllegalArgumentException("Cliente already exists");
		} else {
			return this.clienteRepository.save(cliente);
		}
	}
	
	public Cliente save(ClienteNewDTO clienteNewDto) {
		Cliente cliente = mapper.map(clienteNewDto, Cliente.class);
		if (this.clienteRepository.existsByNome(cliente.getNome())) {
			throw new IllegalArgumentException("Cliente already exists");
		} else {
			return this.clienteRepository.save(cliente);
		}
	}

	public List<ClienteDTO> findAll() {
		List<ClienteDTO> clientesDto = this.clienteRepository.findAll().stream().map( 
				(cliente) -> mapper.map(cliente, ClienteDTO.class)).collect(Collectors.toList());
		return clientesDto;
	}
}