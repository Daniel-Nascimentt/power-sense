package br.com.power.sense.dto.request;

import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.enums.VoltagemEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletrodomesticoRequest {

	@NotBlank
    private String nome;
    @NotBlank
    private String modelo;
    @NotNull
    private Long potencia;
    @NotNull
    private VoltagemEnum voltagemEnum;
    
    public EletrodomesticoModel toModel() {
    	return new EletrodomesticoModel(this.nome, this.modelo, this.potencia, this.voltagemEnum);
    }
    
}