package br.com.power.sense.controller;

import java.net.URI;
import java.util.List;

import br.com.power.sense.dto.request.ConsumoRequest;
import br.com.power.sense.exceptions.CpfNotFoundException;
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
	public ResponseEntity<?> detalharEletrodomestico(@PathVariable(value = "id") Long id) {

		EletrodomesticoResponse eletrodomesticoResponse = service.obterEletrodomesticoPorId(id);

		return ResponseEntity.ok(eletrodomesticoResponse);
	}


	@GetMapping(value = "/buscarTodos/{cpf}")
	public ResponseEntity<List<EletrodomesticoResponse>> listarEletrodomesticosPorCPF(@PathVariable("cpf") String cpf) throws CpfNotFoundException {
		return ResponseEntity.ok(service.obterTodosPorCpf(cpf));
	}

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<EletrodomesticoResponse> cadastrarEletrodomestico (@RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest, UriComponentsBuilder uriBuilder){
        
    	EletrodomesticoResponse eletrodomesticoResponse = service.cadastrarEletrodomestico(eletrodomesticoRequest);
        URI uriLocation = uriBuilder.path("/eletrodomesticos/buscar/{id}").buildAndExpand(eletrodomesticoResponse.getId()).toUri();
      
        return ResponseEntity.created(uriLocation).body(eletrodomesticoResponse);
    
    }

	@PutMapping(value = "/atualiziar")
	public ResponseEntity<?> atualizarEletrodomestico(@RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest) {

		EletrodomesticoResponse eletrodomenticoAtualizado = service.atualizarEletrodomestico(eletrodomesticoRequest);

		return ResponseEntity.ok().body(eletrodomenticoAtualizado);
	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<?> excluirEletrodomestico(@PathVariable("id") Long id) throws DatabaseException {
		service.excluirEletrodomestico(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/consumo/{idEletrodomestico}")
	public ResponseEntity<?> obterConsumoEletrodomestico(@PathVariable(value = "idEletrodomestico") Long id) {

		EletrodomesticoResponse eletrodomesticoResponse = service.obterConsumoEletrodomestico(id);

		return ResponseEntity.ok(eletrodomesticoResponse);
	}

	@PostMapping(value = "/consumo/{idEletrodomestico}")
	public ResponseEntity<?> reportarConsumo(@RequestBody @Valid ConsumoRequest consumoRequest){

		service.reportarConsumoEletrodomestico(consumoRequest);

		return ResponseEntity.ok().build();

	}
}