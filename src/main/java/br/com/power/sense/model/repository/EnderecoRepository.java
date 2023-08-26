package br.com.power.sense.model.repository;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
    public List<EnderecoModel> findByContratante(ContratanteModel contratanteModel);

    List<EnderecoModel> findByCep(String cep);
}
