package br.com.divulgatudo.exception;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;

}
