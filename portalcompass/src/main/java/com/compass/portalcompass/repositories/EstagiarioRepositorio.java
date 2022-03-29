package com.compass.portalcompass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.enums.TipoBolsa;

@Transactional
@Repository
public interface EstagiarioRepositorio extends JpaRepository<Estagiario, Long>{

	List<EstagiarioDTO> findByTipoBolsa(TipoBolsa tipoBolsa);

}
