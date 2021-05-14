package br.com.divulgatudo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.divulgatudo.dto.ClienteDTO;
import br.com.divulgatudo.dto.ClienteNewDTO;
import br.com.divulgatudo.model.Cliente;
import br.com.divulgatudo.service.ClienteService;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteNewDTO clienteNewDto) {
		ClienteDTO clienteDto = mapper.map(this.clienteService.save(clienteNewDto), ClienteDTO.class);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDto.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		Cliente cliente = this.clienteService.findById(id);
		return ResponseEntity.ok(mapper.map(cliente, ClienteDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> clientesDto = this.clienteService.findAll();
		return ResponseEntity.ok(clientesDto);
	}
	
}
