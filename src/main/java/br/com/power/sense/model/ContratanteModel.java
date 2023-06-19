package br.com.power.sense.model;

import java.time.LocalDate;

import br.com.power.sense.model.abstracts.Pessoa;
import br.com.power.sense.model.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratanteModel extends Pessoa {

	public ContratanteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo) {
		super(nome, cpf, dataNascimento, sexo);
	}
	
}
