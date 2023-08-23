package br.com.power.sense.controller.advice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.power.sense.dto.response.ErrorResponseDetails;
import br.com.power.sense.exceptions.CpfNotFoundException;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		List<String> fieldsError = new ArrayList<>();
		
		ex.getFieldErrors().forEach(f -> fieldsError.add("PARAMETRO: [" + f.getField() + "] Mensagem: [" + f.getDefaultMessage() + "]"));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
				"Por favor, verifique se todos os campos foram preenchidos corretamente!", 
				HttpStatus.BAD_REQUEST.value(), 
				fieldsError,
				new Date().getTime()));
	}

	@ExceptionHandler(CpfNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> CpfNotFoundExceptionException(CpfNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
				"CPF não encontrado", 
				HttpStatus.BAD_REQUEST.value(), 
				new ArrayList<>(),
				new Date().getTime()));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> ControllerNotFoundException(EntityNotFoundException ex){

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(
				"Entidade não encontrada",
				HttpStatus.NOT_FOUND.value(),
				new ArrayList<>(),
				new Date().getTime()));
	}
	
}