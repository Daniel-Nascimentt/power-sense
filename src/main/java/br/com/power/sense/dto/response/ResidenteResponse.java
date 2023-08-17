package br.com.power.sense.dto.response;

import java.time.LocalDate;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class ResidenteResponse {
	
	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private SexoEnum sexo;
	
	private String parentescoComContratante;
	
	private String cpfContratante;

	public ResidenteResponse(ResidenteModel residenteModel) {
		this.nome = residenteModel.getNome();
		this.cpf = residenteModel.getCpf();
		this.dataNascimento = residenteModel.getDataNascimento();
		this.sexo = residenteModel.getSexo();
	}

}
