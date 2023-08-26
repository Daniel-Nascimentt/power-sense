package br.com.power.sense.model;

import java.time.LocalDate;
import java.util.List;

import br.com.power.sense.model.enums.SexoEnum;
import jakarta.persistence.*;
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

	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contratante")
	private List<ResidenteModel> residentes;

	@OneToMany(mappedBy = "contratanteUtiliza")
	private List<EletrodomesticoModel> eletrosUtilizados;

	@OneToMany(mappedBy = "contratante")
	private List<EnderecoModel> enderecos;

	/**
	 * Para uso do frameword
	 */
	@Deprecated
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