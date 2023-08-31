package br.com.power.sense.controller;

import java.util.List;

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

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.service.EnderecoNotFoundException;
import br.com.power.sense.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<?> cadastrarEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest) throws CpfNotFoundException {

		enderecoService.cadastrarEndereco(enderecoRequest);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PutMapping(value = "/atualizar")
	public ResponseEntity<?> atualizarEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest) throws CpfNotFoundException, EnderecoNotFoundException {

		enderecoService.atualizarEndereco(enderecoRequest);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable Long id) throws EnderecoNotFoundException {

		enderecoService.excluirEndereco(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/buscarTodos/{cep}")
	public ResponseEntity<?> detalharEndereco(@PathVariable String cep) throws EnderecoNotFoundException {

		List<EnderecoResponse> enderecoResponse = enderecoService.buscarPorCep(cep);

		return ResponseEntity.ok().body(enderecoResponse);
	}
    
    @GetMapping(value = "/buscar/{cpf}")
    public ResponseEntity<List<EnderecoResponse>> buscarPorCpf(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

		return ResponseEntity.ok(enderecoService.buscarPorCpf(cpf));

	}

}
