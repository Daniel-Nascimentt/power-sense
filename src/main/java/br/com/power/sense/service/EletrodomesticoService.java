package br.com.power.sense.service;

import java.util.ArrayList;
import java.util.List;

import br.com.power.sense.exceptions.DatabaseException;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.model.repository.EletrodomesticoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.dto.response.EletrodomesticoResponse;

@Service
public class EletrodomesticoService {

    @Autowired
    private EletrodomesticoRepository repository;

    public EletrodomesticoResponse cadastrarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){
        EletrodomesticoModel eletrodomestico = eletrodomesticoRequest.toModel();
        var eletroSave = repository.save(eletrodomestico);
        return new EletrodomesticoResponse(eletroSave);
    }

     public EletrodomesticoResponse atualizarEletrodomestico(Long id, EletrodomesticoRequest eletrodomesticoRequest){
    	try{
            EletrodomesticoModel buscaEletro = repository.getReferenceById(id);
            buscaEletro.setNome(eletrodomesticoRequest.getNome());
            buscaEletro.setModelo(eletrodomesticoRequest.getModelo());
            buscaEletro.setPotencia(eletrodomesticoRequest.getPotencia());
            buscaEletro.setVoltagemEnum(eletrodomesticoRequest.getVoltagemEnum());
            buscaEletro = repository.save(buscaEletro);
            return new EletrodomesticoResponse(buscaEletro);
        } catch (EntityNotFoundException e) {
            throw  new EntityNotFoundException("Eletrodomestico não encontrado");
        }
    }

    public void excluirEletrodomestico(Long id) throws DatabaseException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Eletrodomestico não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base");
        }
    }

    public EletrodomesticoResponse obterEletrodomesticoPorId(Long id) {
        EletrodomesticoModel eletrodomestico = repository.getById(id);
        return new EletrodomesticoResponse(eletrodomestico);
    }

    public Page<EletrodomesticoResponse> obterTodos(Pageable paginacao) {
         var eletrodomestico = repository.findAll(paginacao);
    	 return eletrodomestico.map(eletro -> new EletrodomesticoResponse(eletro));    }
}