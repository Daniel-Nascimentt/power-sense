package br.com.power.sense.model;

import java.time.LocalDate;
import java.util.List;

import br.com.power.sense.model.enums.SexoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CONTRATANTE")
public class ContratanteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	private SexoEnum sexo;
	
	@OneToMany(mappedBy = "contratante")
	private List<ResidenteModel> residentes;

	public ContratanteModel() {
	}

	public ContratanteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}

	public ContratanteModel(Long id, String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
	}
}