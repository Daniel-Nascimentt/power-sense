package br.com.power.sense.model;

import br.com.power.sense.dto.request.ConsumoRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CONSUMO")
public class ConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long wattsTotalConsumido;

    @OneToOne
    private EletrodomesticoModel eletrodomestico;

    @NotNull
    private Long horasConsumo;

    /**
     * Para uso do framework
     */
    @Deprecated
    public ConsumoModel(){

    }

    public ConsumoModel(Long horasConsumo, EletrodomesticoModel eletrodomestico){
        this.horasConsumo = horasConsumo;
        this.eletrodomestico = eletrodomestico;

        calculaConsumoTotalWattsPorHora(eletrodomestico);

    }

    public ConsumoModel toUpdate(ConsumoRequest consumoRequest, EletrodomesticoModel eletrodomestico) {

        this.horasConsumo = consumoRequest.getHorasConsumo();
        calculaConsumoTotalWattsPorHora(eletrodomestico);

        return this;
    }

    private void calculaConsumoTotalWattsPorHora(EletrodomesticoModel eletrodomestico) {
        this.wattsTotalConsumido = eletrodomestico.getPotencia() * this.horasConsumo;
    }
}
