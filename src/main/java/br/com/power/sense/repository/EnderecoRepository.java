package br.com.power.sense.repository;

import br.com.power.sense.model.EnderecoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EnderecoRepository {

    private Map<Long, EnderecoModel> enderecos;

    public EnderecoRepository(){
        enderecos = new HashMap<>();
    }

    public EnderecoModel incluir (EnderecoModel endereco){
        Long id = new Random().nextLong(Long.MAX_VALUE);
        endereco.setId(id);
        enderecos.put(id, endereco);
        return enderecos.get(id);
    }

    public Optional<EnderecoModel> consultar (Long id) {
        List<EnderecoModel> listaEnderecosModels = new ArrayList<>();
        for (Map.Entry<Long, EnderecoModel> endereco : enderecos.entrySet()) {
            listaEnderecosModels.add(endereco.getValue());
        }

        return listaEnderecosModels.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Page listarTodos (Pageable paginacao){
        List<EnderecoModel> listaEnderecosModels = new ArrayList<>();
        for (Map.Entry<Long, EnderecoModel> endereco : enderecos.entrySet()) {
            listaEnderecosModels.add(endereco.getValue());
        }
        return new PageImpl(listaEnderecosModels, paginacao, listaEnderecosModels.size());
    }

    public Optional<EnderecoModel> atualizar (Long id, EnderecoModel enderecoModel){
        Optional<EnderecoModel> enderecoConsultado = consultar(id);
        if (enderecoConsultado.isEmpty()){
            return enderecoConsultado;
        }
        return Optional.of(enderecos.replace(id, enderecoModel));
    }

    public Optional<EnderecoModel> excluir (Long id){
        Optional<EnderecoModel> enderecoConsultado = consultar(id);
        if (enderecoConsultado.isEmpty()){
            return enderecoConsultado;
        }
        return Optional.of(enderecos.remove(id));
    }
}