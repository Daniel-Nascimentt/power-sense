package br.com.power.sense.dto.response;

import br.com.power.sense.model.ConsumoModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class ConsumoResponse {

    private Long watts;

    private Long horasConsumo;

    public ConsumoResponse(ConsumoModel consumo) {
        this.watts = consumo.getWattsTotalConsumido();
        this.horasConsumo = consumo.getHorasConsumo();
    }
}
