package br.com.power.sense.controller;

import java.net.URI;

import br.com.power.sense.exceptions.DatabaseException;
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

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.dto.response.EletrodomesticoResponse;
import br.com.power.sense.service.EletrodomesticoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoService service;

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> detalharEletrodomestico(@PathVariable @NotNull Long id) {

		EletrodomesticoResponse eletrodomesticoResponse = service.obterEletrodomesticoPorId(id);

		return ResponseEntity.ok(eletrodomesticoResponse);
	}

    @GetMapping(value = "/buscarTodos")
    public Page<EletrodomesticoResponse> listarEletrodomesticos (@PageableDefault(size = 10) Pageable paginacao){
    	
    	Page<EletrodomesticoResponse> eletrodomesticos = service.obterTodos(paginacao);
    	
        return eletrodomesticos;
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<EletrodomesticoResponse> cadastrarEletrodomestico (@RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest, UriComponentsBuilder uriBuilder){
        
    	EletrodomesticoResponse eletrodomesticoResponse = service.cadastrarEletrodomestico(eletrodomesticoRequest);
        URI endereco = uriBuilder.path("/eletrodomesticos/buscar/{id}").buildAndExpand(eletrodomesticoResponse.getId()).toUri();
      
        return ResponseEntity.created(endereco).body(eletrodomesticoResponse);
    
    }

	@PutMapping(value = "/atualiziar/{id}")
	public ResponseEntity<?> atualizarEletrodomestico(@PathVariable @NotNull Long id, @RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest) {

		EletrodomesticoResponse atualizarEletrodomestico = service.atualizarEletrodomestico(id, eletrodomesticoRequest);

		return ResponseEntity.ok().body(atualizarEletrodomestico);
	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<?> excluirEletrodomestico(@PathVariable @NotNull Long id) throws DatabaseException {
		service.excluirEletrodomestico(id);
		return ResponseEntity.ok().build();
	}
}