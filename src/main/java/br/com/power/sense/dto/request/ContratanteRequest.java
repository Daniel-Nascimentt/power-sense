package br.com.power.sense.dto.request;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.enums.SexoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratanteRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	@CPF
	private String cpf;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull
	private LocalDate dataNascimento;
	
	@NotNull
	private SexoEnum sexo;

	/**
	 * Para uso do framework
	 */
	@Deprecated
	public ContratanteRequest(){

	}

	public ContratanteRequest(String cpf) {
		this.cpf = cpf;
	}

	public ContratanteModel toModel() {
		return new ContratanteModel(this.nome, this.cpf, this.dataNascimento, this.sexo);
	}
}