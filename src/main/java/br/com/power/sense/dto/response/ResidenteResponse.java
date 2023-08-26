package br.com.power.sense.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.SexoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Schema
public class ResidenteResponse {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String cpf;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate dataNascimento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SexoEnum sexo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String parentescoComContratante;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ContratanteResponse contratante;

	public ResidenteResponse (){

	}

    public List<ResidenteResponse> toListResponse (List<ResidenteModel> residentesEncontrados) {

		List<ResidenteResponse> response = new ArrayList<>();

		residentesEncontrados.forEach(r -> 	response.add(this.toResponse(r)));

		return response;

    }

	public ResidenteResponse toResponse(ResidenteModel residenteModel) {

		this.nome = residenteModel.getNome();
		this.cpf = residenteModel.getCpf();
		this.dataNascimento = residenteModel.getDataNascimento();
		this.sexo = residenteModel.getSexo();
		this.parentescoComContratante = residenteModel.getParentescoComContratante();

		ContratanteModel contratanteModel = residenteModel.getContratante();

		this.contratante = new ContratanteResponse();

		this.contratante.setNome(contratanteModel.getNome());
		this.contratante.setCpf(contratanteModel.getCpf());

		return this;

	}


	public ResidenteResponse toResponseEletrodomestico(ResidenteModel residenteModel) {
		this.nome = residenteModel.getNome();
		this.cpf = residenteModel.getCpf();

		return this;
	}

}