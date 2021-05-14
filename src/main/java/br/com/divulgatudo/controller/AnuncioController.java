package br.com.divulgatudo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.divulgatudo.dto.AnuncioDTO;
import br.com.divulgatudo.service.AnuncioService;

@RestController
@RequestMapping("/v1/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioService anuncioService;
	
	@GetMapping
	public ResponseEntity<List<AnuncioDTO>> search (
			@RequestParam(name = "clienteId", required = false) Long clienteId,
			@RequestParam(name = "dataInicio", required = false) 
				@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(name = "dataTermino", required = false) 
				@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataTermino
			) {
		List<AnuncioDTO> dto = this.anuncioService.search(clienteId, dataInicio, dataTermino);
		return ResponseEntity.ok(dto);
	}
}