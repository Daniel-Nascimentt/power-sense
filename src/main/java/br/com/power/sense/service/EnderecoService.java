package br.com.power.sense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.exceptions.EnderecoNotFoundException;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.repository.ContratanteRepository;
import br.com.power.sense.model.repository.EnderecoRepository;
import br.com.power.sense.model.repository.ResidenteRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private ContratanteRepository contratanteRepository;
    
    @Autowired
    private ResidenteRepository residenteRepository;

    public void cadastrarEndereco(EnderecoRequest enderecoRequest) throws CpfNotFoundException {

        Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(enderecoRequest.getCpfContratante());

        if(!contratante.isPresent()){
            throw new CpfNotFoundException("CPF do Contratante não localizado");
        }

        enderecoRequest.toModelAndSave(contratante.get(), residenteRepository, enderecoRepository);

    }

    public void atualizarEndereco(EnderecoRequest enderecoRequest) throws CpfNotFoundException, EnderecoNotFoundException {

        Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(enderecoRequest.getCpfContratante());

        if(!contratante.isPresent()){
            throw new CpfNotFoundException("CPF do Contratante não localizado");
        }

        Optional<EnderecoModel> endereco = enderecoRepository.findById(enderecoRequest.getId());

        if(!endereco.isPresent()){
            throw new EnderecoNotFoundException("Endereço não localizado");
        }

        enderecoRequest.toUpdateAndSave(endereco.get(), residenteRepository, enderecoRepository);

    }

    public void excluirEndereco(Long id) throws EnderecoNotFoundException {

        Optional<EnderecoModel> endereco = enderecoRepository.findById(id);

        if(!endereco.isPresent()){
            throw new EnderecoNotFoundException("Endereço não localizado");
        }

        enderecoRepository.delete(endereco.get());


    }

    public List<EnderecoResponse> buscarPorCep(String cep) throws EnderecoNotFoundException {

        List<EnderecoResponse> listResponse = new ArrayList<>();

        List<EnderecoModel> enderecos = enderecoRepository.findByCep(cep);

        enderecos.forEach(end -> listResponse.add(new EnderecoResponse(end)));

        return listResponse;
    }

    public List<EnderecoResponse> buscarPorCpf(String cpf) throws CpfNotFoundException {

        List<EnderecoResponse> listResponse = new ArrayList<>();

        Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(cpf);

        if(contratante.isPresent()){
        	 var endereco = enderecoRepository.findByContratante(contratante.get());
             endereco.forEach(end -> listResponse.add(new EnderecoResponse(end)));
             return listResponse;
        }
        
        Optional<ResidenteModel> residente = residenteRepository.findByCpf(cpf);

        if(residente.isPresent()){
        	var endereco = enderecoRepository.findByResidente(residente.get());
            endereco.forEach(end -> listResponse.add(new EnderecoResponse(end)));
            return listResponse;
        }

        throw new CpfNotFoundException("CPF não localizado");


    }
}