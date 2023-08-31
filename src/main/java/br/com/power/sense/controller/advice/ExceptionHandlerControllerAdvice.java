package br.com.power.sense.controller.advice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import br.com.power.sense.exceptions.*;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.power.sense.dto.response.ErrorResponseDetails;


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
	public ResponseEntity<?> CpfNotFoundException(CpfNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
				"CPF não encontrado", 
				HttpStatus.BAD_REQUEST.value(),
				Arrays.asList(ex.getMessage()),
				new Date().getTime()));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> EntityNotFoundException(EntityNotFoundException ex){

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(
				"Entidade não encontrada",
				HttpStatus.NOT_FOUND.value(),
				Arrays.asList(ex.getMessage()),
				new Date().getTime()));
	}


	@ExceptionHandler(NomeNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> nomeNotFoundException(NomeNotFoundException ex){

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(
				"Nome não encontrado",
				HttpStatus.NOT_FOUND.value(),
				Arrays.asList(ex.getMessage()),
				new Date().getTime()));
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> genericException(Exception ex) {
		return new ResponseEntity<>("Algo Inesperado ocorreu.", HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(ResidenteInvalidoException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> residenteInvalidoException(ResidenteInvalidoException ex){

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
				"Esse CPF já é cadastrado como contratante.",
				HttpStatus.BAD_REQUEST.value(),
				Arrays.asList(ex.getMessage()),
				new Date().getTime()));
	}

	@ExceptionHandler(EnderecoNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> enderecoNotFoundException(EnderecoNotFoundException ex){

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(
				"Endereço não encontrado.",
				HttpStatus.NOT_FOUND.value(),
				Arrays.asList(ex.getMessage()),
				new Date().getTime()));
	}
}