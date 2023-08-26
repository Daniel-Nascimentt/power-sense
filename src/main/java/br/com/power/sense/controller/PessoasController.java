package br.com.power.sense.controller;

import br.com.power.sense.exceptions.*;
import br.com.power.sense.model.enums.SexoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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

import java.time.LocalDate;

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
    public ResponseEntity<?> cadastrarNovoResidente(@RequestBody @Valid ResidenteRequest request) throws CpfNotFoundException {

        pessoasService.salvarResidente(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/buscarContratante/{cpf}")
    public ResponseEntity<ContratanteResponse> buscarContratante(@PathVariable("cpf") String cpf) {

        return ResponseEntity.ok(pessoasService.buscarContratante(cpf));

    }


    @GetMapping(value = "/buscarResidente/{cpf}")
    public ResponseEntity<ResidenteResponse> buscarResidente(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

        ResidenteResponse response = pessoasService.buscarResidentePorCpf(cpf);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/atualizarContratante/{cpf}")
    public ResponseEntity<?> atualizarContratante(@PathVariable("cpf") String cpf) {

        pessoasService.atualizarContratante(cpf);

        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/atualizarResidente/{cpf}")
    public ResponseEntity<?> atualizarResidente(@PathVariable("cpf") String cpf,
												@RequestBody @Valid ResidenteRequest request) throws CpfNotFoundException {

        pessoasService.atualizarResidente(cpf, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deletarContratante/{cpf}")
    public ResponseEntity<?> deletarContratante(@PathVariable("cpf") String cpf) {

        pessoasService.deletarContratante(cpf);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/deletarResidente/{cpf}")
    public ResponseEntity<?> deletarResidente(@PathVariable("cpf") String cpf) throws CpfNotFoundException {

        pessoasService.deletarResidente(cpf);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/nomes/{nome}/residentes")
    public Page<ResidenteResponse> buscarResidentePorNome(@PathVariable("nome") String nome,
                                                          @PageableDefault(size = 10) Pageable paginacao) throws NomeNotFoundException {

        Page<ResidenteResponse> residentes = pessoasService.buscarResidentePorNome(nome, paginacao);

        return residentes;
    }

    @GetMapping(value = "/nascimentos/{data}/residentes")
    public Page<ResidenteResponse> buscarResidentePorDataNascimento(
            @PathVariable("data") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data,
            @PageableDefault(size = 10) Pageable paginacao) throws DataNascimentoNotFoundException {

        Page<ResidenteResponse> residentes = pessoasService.buscarResidentePorDataNascimento(data, paginacao);

        return residentes;
    }

    @GetMapping(value = "/sexos/{sexo}/residentes")
    public Page<ResidenteResponse> buscarResidentePorSexo(@PathVariable("sexo") String sexo,
                                                          @PageableDefault(size = 10) Pageable paginacao) throws SexoNotFoundException {

        Page<ResidenteResponse> residentes = pessoasService.buscarResidentePorSexo(sexo, paginacao);

        return residentes;

    }

    @GetMapping(value = "/parentescos/{parentesco}/residentes")
    public Page<ResidenteResponse> buscarResidentePorParentescoComContratante(@PathVariable("parentesco") String parentesco,
                                                                              @PageableDefault(size = 10) Pageable paginacao) throws ParentescoNotFoundException {

        Page<ResidenteResponse> residentes = pessoasService.buscarResidentePorParentesco(parentesco, paginacao);

        return residentes;

    }

    @GetMapping(value = "/contratantes/{cpf}/residentes")
    public Page<ResidenteResponse> buscarResidentePorContratanteVinculado(@PathVariable("cpf") String cpf,
                                                                          @PageableDefault(size = 10) Pageable paginacao) throws CpfNotFoundException {

        ContratanteRequest contratante = new ContratanteRequest();
        contratante.setCpf(cpf);

        Page<ResidenteResponse> residentes = pessoasService.buscarResidentePorContratante(contratante, paginacao);

        return residentes;

    }
}