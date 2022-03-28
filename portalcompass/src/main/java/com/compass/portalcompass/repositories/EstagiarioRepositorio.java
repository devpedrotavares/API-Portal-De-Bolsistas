package com.compass.portalcompass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compass.portalcompass.entities.Estagiario;

@Transactional
@Repository
public interface EstagiarioRepositorio extends JpaRepository<Estagiario, Long>{

}
