package br.com.power.sense.dto.request;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.VoltagemEnum;
import br.com.power.sense.model.repository.ContratanteRepository;
import br.com.power.sense.model.repository.ResidenteRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class EletrodomesticoRequest {

    private Long id;
	@NotBlank
    private String nome;
    @NotBlank
    private String modelo;
    @NotNull
    private Long potencia;
    @NotNull
    private VoltagemEnum voltagemEnum;

    List<String> utilizadoPorCpfs = new ArrayList<>();
    
    public EletrodomesticoModel toModel(ContratanteRepository contratanteRepository, ResidenteRepository residenteRepository) {

        EletrodomesticoModel eletroModel = new EletrodomesticoModel(this.nome, this.modelo, this.potencia, this.voltagemEnum);

        toModelContratanteEResidente(contratanteRepository, residenteRepository, eletroModel);

        return eletroModel;
    }

    public void toModelContratanteEResidente(ContratanteRepository contratanteRepository, ResidenteRepository residenteRepository, EletrodomesticoModel eletroModel) {
        for (String cpf : this.utilizadoPorCpfs) {

            Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(cpf);
            if (contratante.isPresent()) {
                eletroModel.setContratanteUtiliza(contratante.get());
                continue;
            }

            Optional<ResidenteModel> residente = residenteRepository.findByCpf(cpf);
            if (residente.isPresent()) {
                eletroModel.addResidenteUtilizacao(residente.get());
            }

        }
    }

}