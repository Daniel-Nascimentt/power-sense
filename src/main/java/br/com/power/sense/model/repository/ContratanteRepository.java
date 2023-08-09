package br.com.power.sense.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.power.sense.model.ContratanteModel;


public interface ContratanteRepository extends JpaRepository<ContratanteModel, Long>{

	Optional<ContratanteModel> findByCpf(String cpf);

}
