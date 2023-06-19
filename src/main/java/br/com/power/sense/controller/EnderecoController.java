package br.com.power.sense.controller;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.service.EnderecoService;
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
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> cadastrarEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest,UriComponentsBuilder uriBuilder) {
        EnderecoResponse enderecoResponse = enderecoService.cadastrarEndereco(enderecoRequest);
        URI endereco = uriBuilder.path("/enderecos/{id}").buildAndExpand(enderecoResponse.getId()).toUri();
        return ResponseEntity.created(endereco).body(enderecoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEndereco (@PathVariable @NotNull Long id, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        EnderecoResponse enderecoResponse;

        try {
            enderecoResponse = enderecoService.atualizarEndereco(id, enderecoRequest);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Endereco inexistente");
        }

        return ResponseEntity.ok().body(enderecoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEndereco (@PathVariable @NotNull Long id){
        EnderecoResponse enderecoResponse;

        try {
            enderecoResponse = enderecoService.excluirEndereco(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Endereco inexistente");
        }

        return ResponseEntity.ok().body(enderecoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharEndereco (@PathVariable @NotNull Long id) {
        EnderecoResponse enderecoResponse;

        try {
            enderecoResponse = enderecoService.obterPorID(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Endereco inexistente");
        }

        return ResponseEntity.ok().body(enderecoResponse);
    }
    
    @GetMapping
    public Page<EnderecoResponse> listarTodos (@PageableDefault(size = 10) Pageable paginacao){
        return enderecoService.obterTodos(paginacao);
    }

}
