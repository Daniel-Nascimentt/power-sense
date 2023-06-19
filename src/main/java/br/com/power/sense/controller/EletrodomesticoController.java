package br.com.power.sense.controller;

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.dto.response.EletrodomesticoResponse;
import br.com.power.sense.service.EletrodomesticoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoService service;

    @GetMapping ("/{id}")
    public ResponseEntity<?> detalharEletrodomestico(@PathVariable @NotNull Long id){
        EletrodomesticoResponse eletrodomesticoResponse;

        try {
            eletrodomesticoResponse = service.obterEletrodomesticoPorId(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Eletrodoméstico inexistente");
        }

        return ResponseEntity.ok(eletrodomesticoResponse);
    }

    @GetMapping
    public Page<EletrodomesticoResponse> listarEletrodomesticos (@PageableDefault(size = 10) Pageable paginacao){
        return service.obterTodos(paginacao);
    }

    @PostMapping
    public ResponseEntity<EletrodomesticoResponse> cadastrarEletrodomestico (@RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest, UriComponentsBuilder uriBuilder){
        EletrodomesticoResponse eletrodomesticoResponse = service.cadastrarEletrodomestico(eletrodomesticoRequest);
        URI endereco = uriBuilder.path("/eletrodomesticos/{id}").buildAndExpand(eletrodomesticoResponse.getId()).toUri();
        return ResponseEntity.created(endereco).body(eletrodomesticoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEletrodomestico (@PathVariable @NotNull Long id, @RequestBody @Valid EletrodomesticoRequest eletrodomesticoRequest){

    	EletrodomesticoResponse atualizarEletrodomestico;	
    	
        try {
        	 atualizarEletrodomestico = service.atualizarEletrodomestico(id, eletrodomesticoRequest);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Eletrodoméstico inexistente");
        }

        return ResponseEntity.ok().body(atualizarEletrodomestico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEletrodomestico (@PathVariable @NotNull Long id){
        try {
            service.excluirEletrodomestico(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Eletrodomestico inexistente");
        }
        return ResponseEntity.ok().build();
    }
}