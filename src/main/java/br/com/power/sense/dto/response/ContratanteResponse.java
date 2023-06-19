package br.com.power.sense.dto.response;

import java.time.LocalDate;

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
}
