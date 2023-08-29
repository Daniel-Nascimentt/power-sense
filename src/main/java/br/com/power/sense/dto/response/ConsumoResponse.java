package br.com.power.sense.dto.response;

import br.com.power.sense.model.ConsumoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumoResponse {

    private Long watts;

    private Long horasConsumo;

    public ConsumoResponse(ConsumoModel consumo) {
        this.watts = consumo.getWattsTotalConsumido();
        this.horasConsumo = consumo.getHorasConsumo();
    }
}
