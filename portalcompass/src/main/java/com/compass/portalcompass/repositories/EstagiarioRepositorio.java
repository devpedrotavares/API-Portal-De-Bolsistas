package com.compass.portalcompass.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.enums.TipoBolsa;

@Transactional
@Repository
public interface EstagiarioRepositorio extends JpaRepository<Estagiario, Long>{

	Page<Estagiario> findByTipoBolsa(TipoBolsa tipoBolsa, Pageable pageable);

}
