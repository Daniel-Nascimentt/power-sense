package br.com.power.sense.dto.request;

import java.time.LocalDate;
import java.util.Optional;

import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.model.ContratanteModel;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.SexoEnum;
import br.com.power.sense.model.repository.ContratanteRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidenteRequest {

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
	
	@NotBlank
	private String parentescoComContratante;
	
	@NotNull
	private String cpfContratante;
	
	public ResidenteModel toModelAndFindContratante(ContratanteRepository contratanteRepository) throws CpfNotFoundException {

		Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(this.cpfContratante);

		if(!contratante.isPresent()){
			throw new CpfNotFoundException("CPF contratante não encontrado.");
		}

		return new ResidenteModel(this.nome,
				this.cpf,
				this.dataNascimento,
				this.sexo,
				this.parentescoComContratante,
				contratante.get());
	}
	
}