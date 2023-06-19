package br.com.power.sense.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.power.sense.dto.request.EletrodomesticoRequest;
import br.com.power.sense.dto.response.EletrodomesticoResponse;
import br.com.power.sense.model.EletrodomesticoModel;
import br.com.power.sense.repository.EletrodomesticoRepository;

@Service
public class EletrodomesticoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EletrodomesticoRepository eletrodomesticos;

    public EletrodomesticoResponse cadastrarEletrodomestico(EletrodomesticoRequest eletrodomesticoRequest){
        EletrodomesticoModel eletrodomesticoModel = modelMapper.map(eletrodomesticoRequest, EletrodomesticoModel.class);
        eletrodomesticos.incluir(eletrodomesticoModel.getId(), eletrodomesticoModel);
        return modelMapper.map(eletrodomesticoModel, EletrodomesticoResponse.class);
    }

    public EletrodomesticoResponse atualizarEletrodomestico(Long id, EletrodomesticoRequest eletrodomesticoRequest){
        EletrodomesticoModel eletrodomesticoModel = modelMapper.map(eletrodomesticoRequest, EletrodomesticoModel.class);
        eletrodomesticos.atualizar(id, eletrodomesticoModel);
        return modelMapper.map(eletrodomesticoModel, EletrodomesticoResponse.class);
    }

    public EletrodomesticoResponse excluirEletrodomestico(Long id) {
        EletrodomesticoModel eletrodomesticoModel = eletrodomesticos.excluir(id);
        return modelMapper.map(eletrodomesticoModel, EletrodomesticoResponse.class);
    }

    public EletrodomesticoResponse obterEletrodomesticoPorId(Long id) {
        EletrodomesticoModel eletrodomesticoModel = eletrodomesticos.consultar(id);
        return modelMapper.map(eletrodomesticoModel, EletrodomesticoResponse.class);
    }

    public Page<EletrodomesticoResponse> obterTodos (Pageable paginacao) {
        return eletrodomesticos.listarTodos(paginacao).map(p -> modelMapper.map(p, EletrodomesticoResponse.class));
    }
}