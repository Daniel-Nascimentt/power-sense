package br.com.power.sense.dto.request;

import br.com.power.sense.model.ConsumoModel;
import br.com.power.sense.model.EletrodomesticoModel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumoRequest {

    @NotNull
    private Long idEletroDomestico;

    @NotNull
    private Long horasConsumo;


    public ConsumoModel toModel(EletrodomesticoModel eletrodomestico) {
        return new ConsumoModel(horasConsumo, eletrodomestico);
    }
}
