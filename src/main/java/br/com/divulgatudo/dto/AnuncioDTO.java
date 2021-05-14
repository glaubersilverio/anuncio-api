package br.com.divulgatudo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class AnuncioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long clienteId;
	private String clienteNome;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private BigDecimal investimentoPorDia;

}
