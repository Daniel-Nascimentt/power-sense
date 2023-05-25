package br.com.power.sense.model;

import java.time.LocalDate;

import br.com.power.sense.model.abstracts.Pessoa;
import br.com.power.sense.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidenteModel extends Pessoa{

	private String parentescoComContratante;
	
	private String cpfContratante;

	public ResidenteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo, String parentescoComContratante, String cpfContratante) {
		super(nome, cpf, dataNascimento, sexo);
		this.parentescoComContratante = parentescoComContratante;
		this.cpfContratante = cpfContratante;
	}

	
	
}
