package br.com.divulgatudo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteNewDTO {

	@NotEmpty
	@Size(min = 5, max = 50)
	private String nome;
	
}
