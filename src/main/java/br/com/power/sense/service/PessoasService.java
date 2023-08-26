package br.com.power.sense.service;

import br.com.power.sense.dto.response.ResidenteResponse;
import br.com.power.sense.exceptions.*;
import br.com.power.sense.model.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.ContratanteRequest;
import br.com.power.sense.dto.request.ResidenteRequest;
import br.com.power.sense.dto.response.ContratanteResponse;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.repository.ContratanteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoasService {

    @Autowired
    private ContratanteRepository contratanteRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    public void salvarContratante(ContratanteRequest request) {

        ContratanteModel contratante = request.toModel();

        contratanteRepository.save(contratante);

    }

    public void atualizarContratante(String cpf) {

    }

    public ContratanteResponse buscarContratante(String cpf) {

        return null;

    }

    public void deletarContratante(String cpf) {

    }

    public void salvarResidente(ResidenteRequest request) throws CpfNotFoundException, ResidenteInvalidoException {

        Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(request.getCpf());

        if(contratante.isPresent()){
            throw new ResidenteInvalidoException("Esse CPF já é cadastrado como contratante.");
        }

        ResidenteModel residente = request.toModel(contratanteRepository);

        Optional<ContratanteModel> possivelContratanteVinculado =
                contratanteRepository.findByCpf(residente.getContratante().getCpf());

        if (possivelContratanteVinculado.isEmpty()) {
            throw new CpfNotFoundException("CPF do Contratante não localizado");
        }

        residente.getContratante().setId(possivelContratanteVinculado.get().getId());
        residenteRepository.save(residente);

    }

    public void atualizarResidente(String cpf, ResidenteRequest request) throws CpfNotFoundException {

        ResidenteModel residenteModel = request.toModel(contratanteRepository);

        Optional<ResidenteModel> possivelResidente = residenteRepository.findByCpf(cpf);

        if (possivelResidente.isEmpty()) {
            throw new CpfNotFoundException("CPF do Residente não localizado");
        }

        residenteModel.setId(possivelResidente.get().getId());

        Optional<ContratanteModel> possivelContratanteVinculado =
                contratanteRepository.findByCpf(residenteModel.getContratante().getCpf());

        if (possivelContratanteVinculado.isEmpty()) {
            throw new CpfNotFoundException("CPF do Contratante não localizado");
        }

        residenteModel.getContratante().setId(possivelContratanteVinculado.get().getId());

        residenteRepository.save(residenteModel);

    }

    public void deletarResidente(String cpf) throws CpfNotFoundException {

        Optional<ResidenteModel> possivelResidente = residenteRepository.findByCpf(cpf);

        if (possivelResidente.isEmpty()) {
            throw new CpfNotFoundException("CPF do Residente não Localizado");
        }

        residenteRepository.deleteById(possivelResidente.get().getId());

    }

    public ResidenteResponse buscarResidentePorCpf(String cpf) throws CpfNotFoundException {

        Optional<ResidenteModel> possivelResidente =
                residenteRepository.findByCpf(cpf);

        if (possivelResidente.isEmpty()) {
            throw new CpfNotFoundException("CPF do Residente não localizado");
        }

        return new ResidenteResponse().toResponse(possivelResidente.get());

    }

    public Page<ResidenteResponse> buscarResidentePorNome(String nome, Pageable paginacao) throws NomeNotFoundException {

        List<ResidenteModel> residentesEncontrados = residenteRepository.findByNome(nome);

        if (residentesEncontrados.isEmpty()) {
            throw new NomeNotFoundException("Nome do Residente não Localizado");
        }

        var residentesResponse = this.residentesModelToResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }


    public Page<ResidenteResponse> listaResidentesPorContratante(ContratanteRequest contratanteRequest, Pageable paginacao) throws CpfNotFoundException {

        Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(contratanteRequest.getCpf());

        if (!contratante.isPresent()) {
            throw new CpfNotFoundException("CPF do Contratante não Localizado");
        }

        List<ResidenteModel> residentesEncontrados = residenteRepository.findByContratante(contratante.get());

        var residentesResponse = new ResidenteResponse().toListResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }

    public ArrayList<ResidenteResponse> residentesModelToResponse(List<ResidenteModel> residentes) {
        var residentesResponse = new ArrayList<ResidenteResponse>();

        for (ResidenteModel residente : residentes) {
            residentesResponse.add(new ResidenteResponse().toResponse(residente));
        }

        return residentesResponse;
    }

}