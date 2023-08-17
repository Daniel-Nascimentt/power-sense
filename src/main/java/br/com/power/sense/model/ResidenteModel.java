package br.com.power.sense.model;

import java.time.LocalDate;

import br.com.power.sense.model.enums.SexoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "RESIDENTE")
public class ResidenteModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	private SexoEnum sexo;

	private String parentescoComContratante;

	@ManyToOne
	private ContratanteModel contratanteModel;	
	
	public ResidenteModel() {
	}

	public ResidenteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo, String parentescoComContratante, ContratanteModel contratanteModel) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.parentescoComContratante = parentescoComContratante;
		this.contratanteModel = contratanteModel;
	}

	

}
