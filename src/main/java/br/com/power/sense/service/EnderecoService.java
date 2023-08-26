package br.com.power.sense.service;

import br.com.power.sense.dto.request.EnderecoRequest;
import br.com.power.sense.dto.response.EnderecoResponse;
import br.com.power.sense.exceptions.DatabaseException;
import br.com.power.sense.model.EnderecoModel;
import br.com.power.sense.model.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public void cadastrarEndereco(EnderecoRequest enderecoRequest) {

        EnderecoModel enderecoModel = enderecoRequest.toModel();
        enderecoRepository.save(enderecoModel);
    }

    public void atualizarEndereco(Long id, EnderecoRequest enderecoRequest) {
        try {
            EnderecoModel buscaEndereco = enderecoRepository.getReferenceById(id);
            buscaEndereco.setRua(enderecoRequest.getRua());
            buscaEndereco.setNumero(enderecoRequest.getNumero());
            buscaEndereco.setBairro(enderecoRequest.getBairro());
            buscaEndereco.setCidade(enderecoRequest.getCidade());
            buscaEndereco.setEstado(enderecoRequest.getEstado());
            enderecoRepository.save(buscaEndereco);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Endereço não encontrato");
        }
    }

    public void excluirEndereco(Long id) throws DatabaseException {

        try {
            enderecoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Endereço não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base");
        }

    }

    public EnderecoResponse buscarPorId(Long id) {

        EnderecoModel enderecoModel = enderecoRepository.getReferenceById(id);
        return new EnderecoResponse(enderecoModel);
    }

    public Page<EnderecoResponse> obterTodos(Pageable paginacao) {

        var endereco = enderecoRepository.findAll(paginacao);
        return endereco.map(end -> new EnderecoResponse(end));
    }
}