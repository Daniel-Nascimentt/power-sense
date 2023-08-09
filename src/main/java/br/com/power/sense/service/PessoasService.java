package br.com.power.sense.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.ContratanteRequest;
import br.com.power.sense.dto.request.ResidenteRequest;
import br.com.power.sense.dto.response.ContratanteResponse;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.repository.ContratanteRepository;

@Service
public class PessoasService {

	@Autowired
	private ContratanteRepository contratanteRepository;
	
	public void salvarContratante(ContratanteRequest request) {
		
		ContratanteModel contratante = request.toModel();
		
		contratanteRepository.save(contratante);
		
	}

	public void salvarResidente(ResidenteRequest request) {

		ResidenteModel residente = request.toModel();
		
	}

	public ContratanteResponse buscarContratante(String cpf) {
		
		Optional<ContratanteModel> possivelContratante = contratanteRepository.findByCpf(cpf);
		
		if(!possivelContratante.isPresent()) {
			// devolve not found
		}
		
		return new ContratanteResponse(possivelContratante.get());
		
	}

	public void buscatResidente(String cpf) {
		
	}

	public void atualizarContratante(String cpf) {
		
		Optional<ContratanteModel> possivelContratante = contratanteRepository.findByCpf(cpf);

		if(!possivelContratante.isPresent()) {
			// devolve not found
		}
		
	}

	public void atualizarResidente(String cpf) {
		
	}

	public void deletarContratante(String cpf) {
		
	}

	public void deletarResidente(String cpf) {
		
	}

}
