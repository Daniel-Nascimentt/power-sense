package br.com.power.sense.repository;

import br.com.power.sense.model.EletrodomesticoModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EletrodomesticoRepository {

    private Map<Long, EletrodomesticoModel> eletrodomesticos;

    public EletrodomesticoRepository (){
        eletrodomesticos = new HashMap<>();
    }

    public EletrodomesticoModel incluir (Long id, EletrodomesticoModel eletrodomesticoModel){
        return eletrodomesticos.put(id, eletrodomesticoModel);
    }

    public EletrodomesticoModel atualizar (Long id, EletrodomesticoModel eletrodomesticoModel) {
        if (!eletrodomesticos.containsKey(id)){
            throw new EntityNotFoundException();
        }

        eletrodomesticoModel.setId(id);
        return eletrodomesticos.replace(id, eletrodomesticoModel);
    }

    public EletrodomesticoModel excluir (Long id) {
        if (!eletrodomesticos.containsKey(id)){
            throw new EntityNotFoundException();
        }

        return eletrodomesticos.remove(id);
    }

    public EletrodomesticoModel consultar (Long id){
        if (!eletrodomesticos.containsKey(id)){
            throw new EntityNotFoundException();
        }

        return eletrodomesticos.get(id);
    }

    public Page<EletrodomesticoModel> listarTodos (Pageable paginacao) {
        List<EletrodomesticoModel> listaEletrodomesticoModels = new ArrayList<>();

        for (Map.Entry<Long, EletrodomesticoModel> eletrodomestico : eletrodomesticos.entrySet()) {
                listaEletrodomesticoModels.add(eletrodomestico.getValue());
        }

         return new PageImpl<>(listaEletrodomesticoModels, paginacao, listaEletrodomesticoModels.size());
    }
}