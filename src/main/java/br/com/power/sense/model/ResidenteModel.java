package br.com.power.sense.model;

import java.time.LocalDate;

import br.com.power.sense.model.enums.SexoEnum;

public class ResidenteModel {

	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private SexoEnum sexo;
	
	private String parentescoComContratante;
	
	private String cpfContratante;

	public ResidenteModel() {
	}

	public ResidenteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo, String parentescoComContratante, String cpfContratante) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.parentescoComContratante = parentescoComContratante;
		this.cpfContratante = cpfContratante;
	}

	
	
}
