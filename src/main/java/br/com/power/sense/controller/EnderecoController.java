package br.com.power.sense.controller;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.exceptions.DatabaseException;
import br.com.power.sense.service.EnderecoNotFoundException;
import br.com.power.sense.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
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

	@GetMapping(value = "/buscar/{cep}")
	public ResponseEntity<?> detalharEndereco(@PathVariable String cep) throws EnderecoNotFoundException {

		List<EnderecoResponse> enderecoResponse = enderecoService.buscarPorCep(cep);

		return ResponseEntity.ok().body(enderecoResponse);
	}
    
    @GetMapping(value = "/buscarTodos/{cpf}")
    public ResponseEntity<List<EnderecoResponse>> listarTodosPorCpf(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

		return ResponseEntity.ok(enderecoService.obterTodos(cpf));

	}

}
