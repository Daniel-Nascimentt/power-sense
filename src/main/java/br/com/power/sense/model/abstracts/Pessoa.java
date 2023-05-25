package br.com.power.sense.model.abstracts;

import java.time.LocalDate;

import br.com.power.sense.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Pessoa {

	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private SexoEnum sexo;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
	
	
}
