package br.com.divulgatudo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AnuncioNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long clienteId;
	@NotNull
	private LocalDate dataInicio;
	@NotNull
	private LocalDate dataTermino;
	@NotNull
	private BigDecimal investimentoPorDia;

}
