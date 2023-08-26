package br.com.power.sense.dto.response;

import br.com.power.sense.model.EnderecoModel;
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

    public EnderecoResponse(EnderecoModel enderecoModel) {
        this.id = enderecoModel.getId();
        this.rua = enderecoModel.getRua();
        this.numero = enderecoModel.getNumero();
        this.bairro = enderecoModel.getBairro();
        this.cidade = enderecoModel.getCidade();
        this.estado = enderecoModel.getEstado();
    }
}