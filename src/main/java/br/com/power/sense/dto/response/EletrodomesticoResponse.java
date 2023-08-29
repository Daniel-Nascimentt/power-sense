package br.com.power.sense.dto.response;

import br.com.power.sense.model.ConsumoModel;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.enums.VoltagemEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema
public class EletrodomesticoResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modelo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long potencia;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VoltagemEnum voltagem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConsumoResponse consumo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContratanteResponse contratanteUtiliza;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ResidenteResponse> residentesUtilizam = new ArrayList<>();


    public EletrodomesticoResponse() {
    }

    public EletrodomesticoResponse(EletrodomesticoModel eletrodomestico) {

        toResponse(eletrodomestico);

    }


    public EletrodomesticoResponse toResponseEletroDomesticoAllConsumidores(EletrodomesticoModel eletroSave) {

        toResponse(eletroSave);

        if(eletroSave.getContratanteUtiliza() != null) {
            this.contratanteUtiliza = new ContratanteResponse().toResponseEletrodomestico(eletroSave.getContratanteUtiliza());
        }

        eletroSave.getResidentesUtilizam().forEach(resid -> {
            this.residentesUtilizam.add(new ResidenteResponse().toResponseEletrodomestico(resid));
        });

        return this;
    }

    public void toResponse(EletrodomesticoModel eletrodomestico) {
        this.id = eletrodomestico.getId();
        this.nome = eletrodomestico.getNome();
        this.modelo = eletrodomestico.getModelo();
        this.potencia = eletrodomestico.getPotencia();
        this.voltagem = eletrodomestico.getVoltagem();
    }

    public EletrodomesticoResponse toResponseAllInformations(EletrodomesticoModel eletrodomestico, ConsumoModel consumo) {
        this.toResponseEletroDomesticoAllConsumidores(eletrodomestico);
        this.consumo = new ConsumoResponse(consumo);

        return this;
    }
}