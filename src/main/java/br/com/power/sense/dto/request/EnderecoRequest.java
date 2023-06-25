package br.com.power.sense.dto.request;

import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.model.enums.EstadoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

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
    
    public EnderecoModel toModel() {
    	return new EnderecoModel(this.rua, this.numero, this.bairro, this.cidade, this.estado);
    }

}
