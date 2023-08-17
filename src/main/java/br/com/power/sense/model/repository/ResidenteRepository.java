package br.com.power.sense.model.repository;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidenteRepository extends JpaRepository<ResidenteModel, Long> {

    Optional<ResidenteModel> findByCpf(String cpf);

    Optional<ResidenteModel> findByNome(String nome);

    Optional<ResidenteModel> findByParentesco(String parentesco);

    Optional<ResidenteModel> findByContratante(ContratanteModel contratante);
}