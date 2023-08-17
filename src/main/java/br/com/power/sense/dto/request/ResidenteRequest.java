package br.com.power.sense.dto.request;

import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.model.ContratanteModel;
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
	
	@NotBlank
	@CPF
	private String cpfContratante;
	
	public ResidenteModel toModel(ContratanteRepository contratanteRepository) throws CpfNotFoundException {
		
		Optional<ContratanteModel> possivelContratante = contratanteRepository.findByCpf(this.cpfContratante);
		
		if(possivelContratante.isPresent()) {
			return new ResidenteModel(this.nome, this.cpf, this.dataNascimento, this.sexo, this.parentescoComContratante, possivelContratante.get());
		}

		throw new CpfNotFoundException("CPF não encontrado.");
		
	}

}
