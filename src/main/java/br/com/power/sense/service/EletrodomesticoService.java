package br.com.power.sense.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.dto.response.EletrodomesticoResponse;

@Service
public class EletrodomesticoService {

    public EletrodomesticoResponse cadastrarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){
        return new EletrodomesticoResponse();
    }

    public EletrodomesticoResponse atualizarEletrodomestico(Long id, EletrodomesticoRequest eletrodomesticoRequest){
    	return new EletrodomesticoResponse();
    }

    public EletrodomesticoResponse excluirEletrodomestico(Long id) {
        return new EletrodomesticoResponse();
    }

    public EletrodomesticoResponse obterEletrodomesticoPorId(Long id) {
        return new EletrodomesticoResponse();
    }

    public Page<EletrodomesticoResponse> obterTodos(Pageable paginacao) {
    	 return new PageImpl<>(new ArrayList<>(), paginacao, new ArrayList<>().size());    }
}