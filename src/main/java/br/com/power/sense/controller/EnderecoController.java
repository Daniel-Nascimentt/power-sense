package br.com.power.sense.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping(value = "cadastrar")
    public ResponseEntity<?> cadastrarEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest, UriComponentsBuilder uriBuilder) {
    	
        EnderecoResponse enderecoResponse = enderecoService.cadastrarEndereco(enderecoRequest);
        URI endereco = uriBuilder.path("/enderecos/buscar/{id}").buildAndExpand(enderecoResponse.getId()).toUri();
        
        return ResponseEntity.created(endereco).body(enderecoResponse);
        
    }

	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid EnderecoRequest enderecoRequest) {

		EnderecoResponse enderecoResponse = enderecoService.atualizarEndereco(id, enderecoRequest);

		return ResponseEntity.ok().body(enderecoResponse);
	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable Long id) {

		EnderecoResponse enderecoResponse = enderecoService.excluirEndereco(id);

		return ResponseEntity.ok().body(enderecoResponse);
	}

	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<?> detalharEndereco(@PathVariable Long id) {

		EnderecoResponse enderecoResponse = enderecoService.buscarPorId(id);

		return ResponseEntity.ok().body(enderecoResponse);
	}
    
    @GetMapping(value = "/buscarTodos")
    public Page<EnderecoResponse> listarTodos (@PageableDefault(size = 10) Pageable paginacao){
    	
    	Page<EnderecoResponse> enderecos = enderecoService.obterTodos(paginacao);
    	
        return enderecos;
    }

}
