package br.com.power.sense.service;

import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.ResidenteRequest;
import br.com.power.sense.dto.request.ContratanteRequest;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.ContratanteModel;

@Service
public class PessoasService {

	public void salvarContratante(ContratanteRequest request) {
		
		ContratanteModel pessoa = request.toModel();
		
	}

	public void salvarResidente(ResidenteRequest request) {

		ResidenteModel residente = request.toModel();
		
	}

	public void buscarContratante(String cpf) {
		
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
