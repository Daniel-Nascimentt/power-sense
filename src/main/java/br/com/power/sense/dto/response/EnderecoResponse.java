package br.com.power.sense.dto.response;

import br.com.power.sense.model.enums.EstadoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class EnderecoResponse {

    @NotNull
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

}