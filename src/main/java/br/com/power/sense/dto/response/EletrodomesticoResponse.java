package br.com.power.sense.dto.response;

import br.com.power.sense.model.enums.VoltagemEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletrodomesticoResponse {
    private Long id;
    private String nome;
    private String modelo;
    private Long potencia;
    private VoltagemEnum voltagemEnum;
}