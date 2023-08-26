package br.com.power.sense.dto.response;

import java.time.LocalDate;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.enums.SexoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class ContratanteResponse {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String cpf;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate dataNascimento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SexoEnum sexo;

	public ContratanteResponse() {
	}

	public ContratanteResponse(ContratanteModel contratanteModel) {
		this.nome = contratanteModel.getNome();
		this.cpf = contratanteModel.getCpf();
		this.dataNascimento = contratanteModel.getDataNascimento();
		this.sexo = contratanteModel.getSexo();
	}

	public ContratanteResponse toResponseEletrodomestico(ContratanteModel contratanteModel){
		this.nome = contratanteModel.getNome();
		this.cpf = contratanteModel.getCpf();

		return this;
	}

}
