package br.com.power.sense.dto.response;

import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.enums.VoltagemEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class EletrodomesticoResponse {
    private Long id;
    private String nome;
    private String modelo;
    private Long potencia;
    private VoltagemEnum voltagemEnum;

    public EletrodomesticoResponse(EletrodomesticoModel eletroSave) {
        this(eletroSave.getId(), eletroSave.getNome(), eletroSave.getModelo(), eletroSave.getPotencia(), eletroSave.getVoltagemEnum());
    }


    public EletrodomesticoResponse(Long id, String nome, String modelo, Long potencia, VoltagemEnum voltagemEnum) {
    }
}