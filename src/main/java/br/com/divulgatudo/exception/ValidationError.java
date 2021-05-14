package br.com.divulgatudo.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	List<FieldMessage> errors = new ArrayList<>();
	
	ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	
	public List<FieldMessage> getErrors() {
		return this.errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(FieldMessage.builder().fieldName(fieldName).message(message).build());
	}

}
