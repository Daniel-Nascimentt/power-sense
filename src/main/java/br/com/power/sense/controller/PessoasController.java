package br.com.power.sense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.power.sense.dto.request.ResidenteRequest;
import br.com.power.sense.dto.request.ContratanteRequest;
import br.com.power.sense.dto.response.ResidenteResponse;
import br.com.power.sense.dto.response.ContratanteResponse;
import br.com.power.sense.service.PessoasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoasController {

	
	@Autowired
	private PessoasService pessoasService;
	
	
	@PostMapping(value = "/novoContratante")
	public ResponseEntity<?> cadastrarNovoContratante(@RequestBody @Valid ContratanteRequest request) {
		
		pessoasService.salvarContratante(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@PostMapping(value = "/novoResidente")
	public ResponseEntity<?> cadastrarNovoResidente(@RequestBody @Valid ResidenteRequest request) {
		
		pessoasService.salvarResidente(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@GetMapping(value = "/buscarContratante/{cpf}")
	public ResponseEntity<ContratanteResponse> buscarContratante(@PathVariable("cpf") String cpf) {
		
		return ResponseEntity.ok(pessoasService.buscarContratante(cpf));
	
	}
	
	
	@GetMapping(value = "/buscarResidente/{cpf}")
	public ResponseEntity<ResidenteResponse> buscarResidente(@PathVariable("cpf") String cpf) {
		
		pessoasService.buscatResidente(cpf);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping(value = "/atualizarContratante/{cpf}")
	public ResponseEntity<?> atualizarContratante(@PathVariable("cpf") String cpf) {
		
		pessoasService.atualizarContratante(cpf);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping(value = "/atualizarResidente/{cpf}")
	public ResponseEntity<?> atualizarResidente(@PathVariable("cpf") String cpf) {
		
		pessoasService.atualizarResidente(cpf);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/deletarContratante/{cpf}")
	public ResponseEntity<?> deletarContratante(@PathVariable("cpf") String cpf) {
		
		pessoasService.deletarContratante(cpf);
		
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping(value = "/deletarResidente/{cpf}")
	public ResponseEntity<?> deletarResidente(@PathVariable("cpf") String cpf) {
		
		pessoasService.deletarResidente(cpf);
		
		return ResponseEntity.ok().build();
	}

	
}
