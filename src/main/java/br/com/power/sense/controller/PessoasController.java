package br.com.power.sense.controller;

import br.com.power.sense.dto.request.ContratanteRequest;
import br.com.power.sense.dto.request.ResidenteRequest;
import br.com.power.sense.dto.response.ContratanteResponse;
import br.com.power.sense.dto.response.ResidenteResponse;
import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.exceptions.NomeNotFoundException;
import br.com.power.sense.exceptions.ResidenteInvalidoException;
import br.com.power.sense.service.PessoasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoasController {

    @Autowired
    private PessoasService pessoasService;



    @PostMapping(value = "/novoContratante")
    public ResponseEntity<?> cadastrarNovoContratante(@RequestBody @Valid ContratanteRequest request) {

        pessoasService.salvarContratante(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping(value = "/novoResidente")
    public ResponseEntity<?> cadastrarNovoResidente(@RequestBody @Valid ResidenteRequest request) throws CpfNotFoundException, ResidenteInvalidoException {

        pessoasService.salvarResidente(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/buscarContratante/{cpf}")
    public ResponseEntity<ContratanteResponse> buscarContratante(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

        return ResponseEntity.ok(pessoasService.buscarContratante(cpf));

    }


    @GetMapping(value = "/buscarResidente/{cpf}")
    public ResponseEntity<ResidenteResponse> buscarResidente(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

        ResidenteResponse response = pessoasService.buscarResidentePorCpf(cpf);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/atualizarContratante")
    public ResponseEntity<?> atualizarContratante(@RequestBody @Valid ContratanteRequest request) throws CpfNotFoundException {

        return ResponseEntity.ok(pessoasService.atualizarContratante(request));
    }


    @PutMapping(value = "/atualizarResidente")
    public ResponseEntity<?> atualizarResidente(@PathVariable("cpf") String cpf, @RequestBody @Valid ResidenteRequest request) throws CpfNotFoundException {

        pessoasService.atualizarResidente(cpf, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deletarContratante/{cpf}")
    public ResponseEntity<?> deletarContratante(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

        pessoasService.deletarContratante(cpf);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/deletarResidente/{cpf}")
    public ResponseEntity<?> deletarResidente(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

        pessoasService.deletarResidente(cpf);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/buscarResidentePorNome/{nome}")
    public Page<ResidenteResponse> buscarResidentePorNome(@PathVariable("nome") String nome, @PageableDefault(size = 10) Pageable paginacao) throws NomeNotFoundException {
        return pessoasService.buscarResidentePorNome(nome, paginacao);
    }


    @GetMapping(value = "/listaResidentes/{cpfContratante}")
    public Page<ResidenteResponse> listaResidentesPorContratante(@PathVariable("cpfContratante") String cpfContratante, @PageableDefault(size = 10) Pageable paginacao) throws CpfNotFoundException {

        return pessoasService.listaResidentesPorContratante(new ContratanteRequest(cpfContratante), paginacao);

    }
}