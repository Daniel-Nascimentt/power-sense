package br.com.power.sense.model;

import br.com.power.sense.model.enums.VoltagemEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EletrodomesticoModel {
    @NotNull
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String modelo;
    @NotNull
    private Long potencia;
    @NotNull
    private VoltagemEnum voltagemEnum;
	public EletrodomesticoModel(@NotBlank String nome, @NotBlank String modelo, @NotNull Long potencia, @NotNull VoltagemEnum voltagemEnum) {
		this.nome = nome;
		this.modelo = modelo;
		this.potencia = potencia;
		this.voltagemEnum = voltagemEnum;
	}
    
    
}
