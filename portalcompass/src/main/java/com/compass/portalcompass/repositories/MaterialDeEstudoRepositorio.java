package com.compass.portalcompass.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compass.portalcompass.entities.MaterialDeEstudo;

@Transactional
@Repository
public interface MaterialDeEstudoRepositorio extends JpaRepository<MaterialDeEstudo, Long>{

	Optional<MaterialDeEstudo> findByUrl(String url);
	
}
