package br.com.power.sense.service;

import br.com.power.sense.dto.response.ResidenteResponse;
import br.com.power.sense.exceptions.*;
import br.com.power.sense.model.enums.SexoEnum;
import br.com.power.sense.model.repository.ResidenteRepository;
import br.com.power.sense.service.converters.StringToSexoEnumConverter;
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

import java.time.LocalDate;
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

    public void salvarResidente(ResidenteRequest request) throws CpfNotFoundException {

        ResidenteModel residente = request.toModel();

        Optional<ContratanteModel> possivelContratanteVinculado =
                contratanteRepository.findByCpf(residente.getContratante().getCpf());

        if (possivelContratanteVinculado.isEmpty()) {
            throw new CpfNotFoundException("CPF do Contratante não localizado");
        }

        residente.getContratante().setId(possivelContratanteVinculado.get().getId());
        residenteRepository.save(residente);

    }

    public void atualizarResidente(String cpf, ResidenteRequest request) throws CpfNotFoundException {

        ResidenteModel residenteModel = request.toModel();

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

        return new ResidenteResponse(possivelResidente.get());

    }

    public Page<ResidenteResponse> buscarResidentePorNome(String nome, Pageable paginacao) throws NomeNotFoundException {

        List<ResidenteModel> residentesEncontrados = residenteRepository.findByNome(nome);

        if (residentesEncontrados.isEmpty()) {
            throw new NomeNotFoundException("Nome do Residente não Localizado");
        }

        var residentesResponse = this.residentesModelToResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }

    public Page<ResidenteResponse> buscarResidentePorDataNascimento(LocalDate dataNascimento, Pageable paginacao) throws DataNascimentoNotFoundException {

        List<ResidenteModel> residentesEncontrados =
                residenteRepository.findByDataNascimento(dataNascimento);

        if (residentesEncontrados.isEmpty()) {
            throw new DataNascimentoNotFoundException("Data de Nascimento do Residente não Localizada");
        }

        var residentesResponse = this.residentesModelToResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }

    public Page<ResidenteResponse> buscarResidentePorParentesco(String parentesco, Pageable paginacao) throws ParentescoNotFoundException {

        List<ResidenteModel> residentesEncontrados =
                residenteRepository.findByParentescoComContratante(parentesco);

        if (residentesEncontrados.isEmpty()) {
            throw new ParentescoNotFoundException("CPF do Residente não Localizado");
        }

        var residentesResponse = this.residentesModelToResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }

    public Page<ResidenteResponse> buscarResidentePorSexo(String sexo, Pageable paginacao) throws SexoNotFoundException {

        SexoEnum sexoEnum = new StringToSexoEnumConverter().convert(sexo);

        List<ResidenteModel> residentesEncontrados =
                residenteRepository.findBySexo(sexoEnum);

        if (residentesEncontrados.isEmpty()) {
            throw new SexoNotFoundException("Sexo do Residente não Localizado");
        }

        var residentesResponse = this.residentesModelToResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }

    public Page<ResidenteResponse> buscarResidentePorContratante(ContratanteRequest contratanteRequest, Pageable paginacao) throws CpfNotFoundException {

        Optional<ContratanteModel> possivelContratante =
                contratanteRepository.findByCpf(contratanteRequest.getCpf());

        if (possivelContratante.isEmpty()) {
            throw new CpfNotFoundException("CPF do Contratante não Localizado");
        }

        List<ResidenteModel> residentesEncontrados =
                residenteRepository.findByContratante(possivelContratante.get());

        if (residentesEncontrados.isEmpty()) {
            throw new CpfNotFoundException("CPF do Residente não Localizado");
        }

        var residentesResponse = this.residentesModelToResponse(residentesEncontrados);

        return new PageImpl<>(residentesResponse, paginacao, residentesResponse.size());

    }

    public ArrayList<ResidenteResponse> residentesModelToResponse(List<ResidenteModel> residentes) {
        var residentesResponse = new ArrayList<ResidenteResponse>();

        for (ResidenteModel residente : residentes) {
            residentesResponse.add(new ResidenteResponse(residente));
        }

        return residentesResponse;
    }

}