package br.com.power.sense.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.model.ResidenteModel;


public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
	
    public List<EnderecoModel> findByContratante(ContratanteModel contratanteModel);
    
    public List<EnderecoModel> findByResidente(ResidenteModel residenteModel);

    List<EnderecoModel> findByCep(String cep);
}
