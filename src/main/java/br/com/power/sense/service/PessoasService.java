package br.com.power.sense.service;

import br.com.power.sense.model.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.ContratanteRequest;
import br.com.power.sense.dto.request.ResidenteRequest;
import br.com.power.sense.dto.response.ContratanteResponse;
import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.repository.ContratanteRepository;

import java.util.Optional;

@Service
public class PessoasService {

	@Autowired
	private ContratanteRepository contratanteRepository;

	@Autowired
	private ResidenteRepository residenteRepository;
	
	public void salvarContratante(ContratanteRequest request) {
		
		ContratanteModel contratante = request.toModel();
		
		contratanteRepository.save(contratante);
		
	}

	public void salvarResidente(ResidenteRequest request) throws CpfNotFoundException {

		ResidenteModel residente = request.toModel();

		Optional<ContratanteModel> possivelContratanteVinculado =
				contratanteRepository.findByCpf(residente.getContratante().getCpf());

		if(!possivelContratanteVinculado.isPresent()){
			throw new CpfNotFoundException("CPF do Contratante não localizado");
		}

		residente.getContratante().setId(possivelContratanteVinculado.get().getId());
		residenteRepository.save(residente);

	}

	public ContratanteResponse buscarContratante(String cpf) {
		
		return null;
		
	}

	public void buscatResidente(String cpf) {
		
	}

	public void atualizarContratante(String cpf) {
		
	}

	public void atualizarResidente(String cpf) {
		
	}

	public void deletarContratante(String cpf) {
		
	}

	public void deletarResidente(String cpf) {
		
	}

}