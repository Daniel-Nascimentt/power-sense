package br.com.power.sense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.exceptions.DatabaseException;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.repository.ContratanteRepository;
import br.com.power.sense.model.repository.EletrodomesticoRepository;
import br.com.power.sense.model.repository.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.dto.response.EletrodomesticoResponse;

@Service
public class EletrodomesticoService {

    @Autowired
    private EletrodomesticoRepository repository;

    @Autowired
    private ContratanteRepository contratanteRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    public EletrodomesticoResponse cadastrarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){
        EletrodomesticoModel eletrodomestico = eletrodomesticoRequest.toModel(contratanteRepository, residenteRepository);
        var eletroSave = repository.save(eletrodomestico);
        return new EletrodomesticoResponse().toResponseEletroDomesticoAll(eletroSave);
    }

     public EletrodomesticoResponse atualizarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){


        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(eletrodomesticoRequest.getId());

         validaSeEletrodomesticoExiste(eletrodomestico);

         EletrodomesticoModel eletrodomesticoModel = eletrodomestico.get();

            if(eletrodomesticoRequest.getUtilizadoPorCpfs().isEmpty()){
                eletrodomesticoModel.toUpdateEletro(eletrodomesticoRequest);
                repository.save(eletrodomesticoModel);
                return new EletrodomesticoResponse().toResponseEletroDomesticoAll(eletrodomesticoModel);
            }

            eletrodomesticoModel.toUpdateAll(eletrodomesticoRequest, contratanteRepository, residenteRepository);
            repository.save(eletrodomesticoModel);

            return new EletrodomesticoResponse().toResponseEletroDomesticoAll(eletrodomesticoModel);

    }

    public void excluirEletrodomestico(Long id) throws DatabaseException {

        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(id);

        validaSeEletrodomesticoExiste(eletrodomestico);

        repository.delete(eletrodomestico.get());

    }

    public EletrodomesticoResponse obterEletrodomesticoPorId(Long id) {
        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(id);
        validaSeEletrodomesticoExiste(eletrodomestico);
        return new EletrodomesticoResponse().toResponseEletroDomesticoAll(eletrodomestico.get());
    }


    public List<EletrodomesticoResponse> obterTodosPorCpf(String cpf) throws CpfNotFoundException {

        List<EletrodomesticoResponse> listResponse = new ArrayList<>();

        Optional<ContratanteModel> contratante = contratanteRepository.findByCpf(cpf);

        if(contratante.isPresent()){
            List<EletrodomesticoModel> eletrodomesticosContratante = repository.findByContratanteUtiliza(contratante.get());
            eletrodomesticosContratante.forEach(elet -> listResponse.add(new EletrodomesticoResponse(elet)));

            return listResponse;
        }

        Optional<ResidenteModel> residente = residenteRepository.findByCpf(cpf);

        if(residente.isPresent()){
            List<EletrodomesticoModel> eletrodomesticosResidente = repository.findByresidentesUtilizam(residente.get());
            eletrodomesticosResidente.forEach(elet -> listResponse.add(new EletrodomesticoResponse(elet)));

            return listResponse;
        }

        throw new CpfNotFoundException("CPF não encontrado!");

    }

    private void validaSeEletrodomesticoExiste(Optional<EletrodomesticoModel> eletrodomestico) {
        if(!eletrodomestico.isPresent()){
            throw new EntityNotFoundException("Eletrodomestico não encontrado!");
        }
    }
}