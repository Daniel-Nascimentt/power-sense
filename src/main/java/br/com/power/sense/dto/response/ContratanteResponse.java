package br.com.power.sense.dto.response;

import java.time.LocalDate;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.enums.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class ContratanteResponse {

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	private SexoEnum sexo;

	public ContratanteResponse() {
	}

	public ContratanteResponse(ContratanteModel contratanteModel) {
		this.nome = contratanteModel.getNome();
		this.cpf = contratanteModel.getCpf();
		this.dataNascimento = contratanteModel.getDataNascimento();
		this.sexo = contratanteModel.getSexo();
	}

}
