package br.com.power.sense.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.power.sense.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratanteModel {

	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private SexoEnum sexo;

	private List<ResidenteModel> residentes = new ArrayList<>();
	
	public ContratanteModel() {
	}

	public ContratanteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
	
}
