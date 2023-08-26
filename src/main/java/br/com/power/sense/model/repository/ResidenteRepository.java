package br.com.power.sense.model.repository;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.SexoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ResidenteRepository extends JpaRepository<ResidenteModel, Long> {

    Optional<ResidenteModel> findByCpf(String cpf);

    List<ResidenteModel> findByNome(String nome);

    List<ResidenteModel> findByContratante(ContratanteModel contratanteModel);
}