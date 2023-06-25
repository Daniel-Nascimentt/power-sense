package br.com.power.sense.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;

@Service
public class EnderecoService {

    public EnderecoResponse cadastrarEndereco(EnderecoRequest enderecoRequest) {
        return new EnderecoResponse();
    }

    public EnderecoResponse atualizarEndereco(Long id, EnderecoRequest enderecoRequest) {
    	 return new EnderecoResponse();
    }

    public EnderecoResponse excluirEndereco(Long id) {
    	 return new EnderecoResponse();
    }

    public EnderecoResponse buscarPorId(Long id) {
    	 return new EnderecoResponse();
    }

    public Page<EnderecoResponse> obterTodos(Pageable paginacao) {
    	 return new PageImpl<>(new ArrayList<>(), paginacao, new ArrayList<>().size());
    }
}