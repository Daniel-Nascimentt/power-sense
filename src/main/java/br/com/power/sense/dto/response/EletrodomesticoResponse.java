package br.com.power.sense.dto.response;

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
    private VoltagemEnum voltagemEnum;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContratanteResponse contratanteUtiliza;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ResidenteResponse> residentesUtilizam = new ArrayList<>();


    public EletrodomesticoResponse() {
    }

    public EletrodomesticoResponse(EletrodomesticoModel eletroSave) {
        toResponseEletroDomestico(eletroSave);

        if(eletroSave.getContratanteUtiliza() != null) {
            this.contratanteUtiliza = new ContratanteResponse().toResponseEletrodomestico(eletroSave.getContratanteUtiliza());
        }

        eletroSave.getResidentesUtilizam().forEach(resid -> {
            this.residentesUtilizam.add(new ResidenteResponse().toResponseEletrodomestico(resid));
        });


    }


    public EletrodomesticoResponse toResponseByCpf(EletrodomesticoModel eletroSave) {
        toResponseEletroDomestico(eletroSave);

        return this;
    }

    private void toResponseEletroDomestico(EletrodomesticoModel eletroSave) {
        this.id = eletroSave.getId();
        this.nome = eletroSave.getNome();
        this.modelo = eletroSave.getModelo();
        this.potencia = eletroSave.getPotencia();
        this.voltagemEnum = eletroSave.getVoltagemEnum();
    }
}