package br.com.power.sense.model.repository;

import br.com.power.sense.model.ConsumoModel;
import br.com.power.sense.model.EletrodomesticoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumoRepository extends JpaRepository<ConsumoModel, Long>{
	Optional<ConsumoModel> findByEletrodomestico(EletrodomesticoModel eletrodomesticoModel);
}
