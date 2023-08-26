package br.com.power.sense.model.repository;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.ResidenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EletrodomesticoRepository extends JpaRepository<EletrodomesticoModel, Long> {
    List<EletrodomesticoModel> findByContratanteUtiliza(ContratanteModel contratanteModel);

    List<EletrodomesticoModel> findByresidentesUtilizam(ResidenteModel residenteModel);
}
