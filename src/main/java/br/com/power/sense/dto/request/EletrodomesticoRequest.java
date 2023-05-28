package br.com.power.sense.dto.request;

import br.com.power.sense.model.enums.VoltagemEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletrodomesticoRequest {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String modelo;
    @NotNull
    private Long potencia;
    @NotNull
    private VoltagemEnum voltagemEnum;
}