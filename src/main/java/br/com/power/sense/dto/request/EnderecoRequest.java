package br.com.power.sense.dto.request;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.EstadoEnum;
import br.com.power.sense.model.repository.EnderecoRepository;
import br.com.power.sense.model.repository.ResidenteRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoRequest {

	private Long id;
	@NotBlank
	private String rua;
	@NotNull
	private Long numero;
	@NotBlank
	private String bairro;
	@NotBlank
	private String cidade;
	@NotNull
	private EstadoEnum estado;
	@NotBlank
	private String cpfContratante;
	@NotBlank
	private String cep;

	private List<String> cpfsResidentes;

	@JsonIgnore
	public void toModelAndSave(ContratanteModel contratanteModel, ResidenteRepository residenteRepository, EnderecoRepository enderecoRepository) throws CpfNotFoundException {

		if (!cpfsResidentes.isEmpty()) {

			for (String cpf : cpfsResidentes) {

				ResidenteModel residente = buscaResidente(cpf, residenteRepository);
				enderecoRepository.save(new EnderecoModel(this.rua, this.numero, this.bairro, this.cidade, this.estado,
						this.cep, contratanteModel, residente));

			}
		}

		enderecoRepository.save(new EnderecoModel(this.rua, this.numero, this.bairro, this.cidade, this.estado, this.cep, contratanteModel));

	}

	@JsonIgnore
	public void toUpdateAndSave(EnderecoModel enderecoModel, ResidenteRepository residenteRepository, EnderecoRepository enderecoRepository) throws CpfNotFoundException {

		if (!cpfsResidentes.isEmpty()) {
			for (String cpf : cpfsResidentes) {

				ResidenteModel residente = buscaResidente(cpf, residenteRepository);
				setFieldsEnderecoModel(enderecoModel);
				enderecoModel.setResidente(residente);

				enderecoRepository.save(enderecoModel);

			}
		}

		setFieldsEnderecoModel(enderecoModel);

		enderecoRepository.save(enderecoModel);
	}

	@JsonIgnore
	private void setFieldsEnderecoModel(EnderecoModel enderecoModel) {
		enderecoModel.setRua(this.rua);
		enderecoModel.setNumero(this.numero);
		enderecoModel.setBairro(this.bairro);
		enderecoModel.setCidade(this.cidade);
		enderecoModel.setEstado(this.estado);
	}

	@JsonIgnore
	private ResidenteModel buscaResidente(String cpf, ResidenteRepository residenteRepository)
			throws CpfNotFoundException {

		Optional<ResidenteModel> residente = residenteRepository.findByCpf(cpf);

		if (!residente.isPresent()) {
			throw new CpfNotFoundException("Residente não encontrado.");
		}

		return residente.get();

	}

}
