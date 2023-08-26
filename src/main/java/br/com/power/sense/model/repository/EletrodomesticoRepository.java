package br.com.power.sense.model.repository;

import br.com.power.sense.model.EletrodomesticoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EletrodomesticoRepository extends JpaRepository<EletrodomesticoModel, Long> {
}
