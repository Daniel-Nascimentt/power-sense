package br.com.power.sense.model.repository;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.enums.SexoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ResidenteRepository extends JpaRepository<ResidenteModel, Long> {

    Optional<ResidenteModel> findByCpf(String cpf);

    Optional<ResidenteModel> findByNome(String nome);

    Optional<ResidenteModel> findByDataNascimento(LocalDate dataNascimento);

    Optional<ResidenteModel> findBySexo(SexoEnum sexo);

    Optional<ResidenteModel> findByParentescoComContratante(String parentescoComContratante);

    Optional<ResidenteModel> findByContratante(ContratanteModel contratanteModel);
}