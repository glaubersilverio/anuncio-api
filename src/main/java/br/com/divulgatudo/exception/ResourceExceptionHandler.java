package br.com.divulgatudo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(NotFoundException exception, HttpServletRequest request) {
		StandardError error = StandardError.builder()
								.error(exception.getMessage())
								.message("Not found")
								.path(request.getRequestURI())
								.status(HttpStatus.NOT_FOUND.value())
								.timestamp(System.currentTimeMillis())
								.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentoException(IllegalArgumentException exception, HttpServletRequest request) {
		StandardError error = StandardError.builder()
						.error(exception.getMessage())
						.message("Bad Request")
						.path(request.getRequestURI())
						.status(HttpStatus.BAD_REQUEST.value())
						.timestamp(System.currentTimeMillis())
						.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); 
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", "Validation Error", request.getRequestURI());
		exception.getBindingResult().getFieldErrors().forEach(
				(error) -> errors.addError(error.getField(), error.getDefaultMessage()) 
				);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
	}
	
}
