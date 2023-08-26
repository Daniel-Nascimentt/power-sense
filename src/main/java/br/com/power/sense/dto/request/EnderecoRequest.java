package br.com.power.sense.dto.request;

import br.com.power.sense.model.ContratanteModel;
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
    @NotBlank
    private String cpfContratante;
    @NotBlank
    private String cep;
    
    public EnderecoModel toModel(ContratanteModel contratanteModel) {
    	return new EnderecoModel(this.rua, this.numero, this.bairro, this.cidade, this.estado, this.cep, contratanteModel);
    }

    public EnderecoModel toUpdate(EnderecoModel enderecoModel) {
        enderecoModel.setRua(this.rua);
        enderecoModel.setNumero(this.numero);
        enderecoModel.setBairro(this.bairro);
        enderecoModel.setCidade(this.cidade);
        enderecoModel.setEstado(this.estado);

        return enderecoModel;
    }
}
