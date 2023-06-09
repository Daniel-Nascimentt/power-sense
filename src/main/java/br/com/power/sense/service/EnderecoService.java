package br.com.power.sense.service;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecos;

    @Autowired
    private ModelMapper modelMapper;

    public EnderecoResponse cadastrarEndereco(EnderecoRequest enderecoRequest) {
        EnderecoModel enderecoModel = modelMapper.map(enderecoRequest, EnderecoModel.class);
        return modelMapper.map(enderecos.incluir(enderecoModel), EnderecoResponse.class);
    }

    public EnderecoResponse atualizarEndereco(Long id, EnderecoRequest enderecoRequest) {
        EnderecoModel enderecoModel = modelMapper.map(enderecoRequest, EnderecoModel.class);
        enderecoModel.setId(id);
        Optional<EnderecoModel> enderecoAtualizado = enderecos.atualizar(id, enderecoModel);
        if (enderecoAtualizado.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return modelMapper.map(obterPorID(id), EnderecoResponse.class);
    }

    public EnderecoResponse excluirEndereco(Long id) {
        Optional<EnderecoModel> enderecoExcluido = enderecos.excluir(id);
        if (enderecoExcluido.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return modelMapper.map(enderecoExcluido.get(), EnderecoResponse.class);
    }

    public EnderecoResponse obterPorID(Long id) {
        Optional<EnderecoModel> enderecoConsultado = enderecos.consultar(id);
        if (enderecoConsultado.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return modelMapper.map(enderecoConsultado.get(), EnderecoResponse.class);
    }

    public Page<EnderecoResponse> obterTodos(Pageable paginacao) {
        return enderecos.listarTodos(paginacao).map(p -> modelMapper.map(p, EnderecoResponse.class));
    }
}