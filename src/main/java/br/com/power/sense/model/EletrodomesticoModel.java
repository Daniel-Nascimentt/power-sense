package br.com.power.sense.model;

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.model.enums.VoltagemEnum;
import br.com.power.sense.model.repository.ContratanteRepository;
import br.com.power.sense.model.repository.ResidenteRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "ELETRODOMESTICO")
public class EletrodomesticoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String modelo;
    @NotNull
    private Long potencia;
    @NotNull
    @Enumerated(EnumType.STRING)
    private VoltagemEnum voltagem;
    @ManyToOne
    private ContratanteModel contratanteUtiliza;
    @ManyToMany
    private List<ResidenteModel> residentesUtilizam = new ArrayList<>();
    @OneToOne(mappedBy = "eletrodomestico")
    private ConsumoModel consumo;

    /**
     * Para uso do framework
     */
    @Deprecated
    public EletrodomesticoModel(){

    }

	public EletrodomesticoModel(@NotBlank String nome, @NotBlank String modelo, @NotNull Long potencia, @NotNull VoltagemEnum voltagem) {
		this.nome = nome;
		this.modelo = modelo;
		this.potencia = potencia;
		this.voltagem = voltagem;
	}

    public void addResidenteUtilizacao(ResidenteModel residenteModel){
        this.residentesUtilizam.add(residenteModel);
    }

    public void toUpdateEletro(EletrodomesticoRequest eletrodomesticoRequest) {
        updateFieldsEletrodomestico(eletrodomesticoRequest);
    }

    public void toUpdateAll(EletrodomesticoRequest eletrodomesticoRequest, ContratanteRepository contratanteRepository, ResidenteRepository residenteRepository) {
        updateFieldsEletrodomestico(eletrodomesticoRequest);
        eletrodomesticoRequest.toModelContratanteEResidente(contratanteRepository, residenteRepository, this);
    }

    private void updateFieldsEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest) {
        this.nome = eletrodomesticoRequest.getNome();
        this.modelo = eletrodomesticoRequest.getModelo();
        this.potencia = eletrodomesticoRequest.getPotencia();
        this.voltagem = eletrodomesticoRequest.getVoltagem();
    }
}
