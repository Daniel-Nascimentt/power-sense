package br.com.power.sense.controller;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.exceptions.DatabaseException;
import br.com.power.sense.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<?> cadastrarEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest) {

		enderecoService.cadastrarEndereco(enderecoRequest);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoRequest enderecoRequest) {

		enderecoService.atualizarEndereco(id, enderecoRequest);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable Long id) throws DatabaseException {

		enderecoService.excluirEndereco(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<?> detalharEndereco(@PathVariable Long id) {

		EnderecoResponse enderecoResponse = enderecoService.buscarPorId(id);

		return ResponseEntity.ok().body(enderecoResponse);
	}
    
    @GetMapping(value = "/buscarTodos")
    public Page<EnderecoResponse> listarTodos (@PageableDefault(size = 10) Pageable paginacao) {

		return enderecoService.obterTodos(paginacao);
	}

}
