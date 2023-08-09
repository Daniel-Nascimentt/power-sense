package br.com.power.sense.model;

import java.time.LocalDate;

import br.com.power.sense.model.abstracts.Pessoa;
import br.com.power.sense.model.enums.SexoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CONTRATANTE")
public class ContratanteModel extends Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public ContratanteModel(String nome, String cpf, LocalDate dataNascimento, SexoEnum sexo) {
		super(nome, cpf, dataNascimento, sexo);
	}
	
}
