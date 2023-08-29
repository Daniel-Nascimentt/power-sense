package br.com.power.sense.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.power.sense.dto.request.ConsumoRequest;
import br.com.power.sense.exceptions.CpfNotFoundException;
import br.com.power.sense.exceptions.DatabaseException;
import br.com.power.sense.model.ConsumoModel;
import br.com.power.sense.model.ContratanteModel;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.ResidenteModel;
import br.com.power.sense.model.repository.ConsumoRepository;
import br.com.power.sense.model.repository.ContratanteRepository;
import br.com.power.sense.model.repository.EletrodomesticoRepository;
import br.com.power.sense.model.repository.ResidenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ConsumoRepository consumoRepository;

    public EletrodomesticoResponse cadastrarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){
        EletrodomesticoModel eletrodomestico = eletrodomesticoRequest.toModel(contratanteRepository, residenteRepository);
        var eletroSave = repository.save(eletrodomestico);
        return new EletrodomesticoResponse().toResponseEletroDomesticoAllConsumidores(eletroSave);
    }

     public EletrodomesticoResponse atualizarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){


        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(eletrodomesticoRequest.getId());

         validaSeEletrodomesticoExiste(eletrodomestico);

         EletrodomesticoModel eletrodomesticoModel = eletrodomestico.get();

            if(eletrodomesticoRequest.getUtilizadoPorCpfs().isEmpty()){
                eletrodomesticoModel.toUpdateEletro(eletrodomesticoRequest);
                repository.save(eletrodomesticoModel);
                return new EletrodomesticoResponse().toResponseEletroDomesticoAllConsumidores(eletrodomesticoModel);
            }

            eletrodomesticoModel.toUpdateAll(eletrodomesticoRequest, contratanteRepository, residenteRepository);
            repository.save(eletrodomesticoModel);

            return new EletrodomesticoResponse().toResponseEletroDomesticoAllConsumidores(eletrodomesticoModel);

    }

    public void excluirEletrodomestico(Long id) throws DatabaseException {

        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(id);

        validaSeEletrodomesticoExiste(eletrodomestico);

        repository.delete(eletrodomestico.get());

    }

    public EletrodomesticoResponse obterEletrodomesticoPorId(Long id) {
        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(id);
        validaSeEletrodomesticoExiste(eletrodomestico);
        return new EletrodomesticoResponse().toResponseEletroDomesticoAllConsumidores(eletrodomestico.get());
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

    public EletrodomesticoResponse obterConsumoEletrodomestico(Long id) {

        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(id);

        validaSeEletrodomesticoExiste(eletrodomestico);

        Optional<ConsumoModel> consumo = consumoRepository.findByEletrodomestico(eletrodomestico.get());

        if(consumo.isPresent()){
            return new EletrodomesticoResponse().toResponseAllInformations(eletrodomestico.get(), consumo.get());
        }

        throw new EntityNotFoundException("Consumo do eletrodomestico não encontrado.");

    }

    public void reportarConsumoEletrodomestico(ConsumoRequest consumoRequest) {

        Optional<EletrodomesticoModel> eletrodomestico = repository.findById(consumoRequest.getIdEletroDomestico());

        validaSeEletrodomesticoExiste(eletrodomestico);

        Optional<ConsumoModel> consumo = consumoRepository.findByEletrodomestico(eletrodomestico.get());

        if(!consumo.isPresent()){
            consumoRepository.save(consumoRequest.toModel(eletrodomestico.get()));
            return;
        }

        consumoRepository.save(consumo.get().toUpdate(consumoRequest, eletrodomestico.get()));

    }
}