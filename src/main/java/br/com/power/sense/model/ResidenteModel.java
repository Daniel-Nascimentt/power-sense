package br.com.power.sense.model;

import java.time.LocalDate;
import java.util.List;

import br.com.power.sense.model.enums.SexoEnum;
import jakarta.persistence.*;
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

	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	private String parentescoComContratante;

	@ManyToOne
	private ContratanteModel contratante;

	@ManyToMany(mappedBy = "residentesUtilizam")
	private List<EletrodomesticoModel> eletrosUtilizados;
	
	public ResidenteModel() {
	}

	public ResidenteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo, String parentescoComContratante, ContratanteModel contratante) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.parentescoComContratante = parentescoComContratante;
		this.contratante = contratante;
	}
}