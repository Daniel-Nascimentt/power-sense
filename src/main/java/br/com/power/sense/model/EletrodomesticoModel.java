package br.com.power.sense.model;

import br.com.power.sense.model.enums.VoltagemEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ELETRODOMESTICO")
public class EletrodomesticoModel {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
