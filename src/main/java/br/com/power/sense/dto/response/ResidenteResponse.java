package br.com.power.sense.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Schema
public class ResidenteResponse {
	
	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private SexoEnum sexo;
	
	private String parentescoComContratante;

	private ContratanteResponse contratante = new ContratanteResponse();;

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

		this.contratante.setNome(contratanteModel.getNome());
		this.contratante.setCpf(contratanteModel.getCpf());
		this.contratante.setDataNascimento(contratanteModel.getDataNascimento());
		this.contratante.setSexo(contratanteModel.getSexo());

		return this;

	}
}