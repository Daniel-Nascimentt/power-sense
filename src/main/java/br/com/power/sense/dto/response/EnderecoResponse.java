package br.com.power.sense.dto.response;

import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.model.enums.EstadoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class EnderecoResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rua;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long numero;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bairro;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cidade;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EstadoEnum estado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cep;

    public EnderecoResponse(EnderecoModel enderecoModel) {
        this.id = enderecoModel.getId();
        this.rua = enderecoModel.getRua();
        this.numero = enderecoModel.getNumero();
        this.bairro = enderecoModel.getBairro();
        this.cidade = enderecoModel.getCidade();
        this.estado = enderecoModel.getEstado();
        this.cep = enderecoModel.getCep();
    }
}