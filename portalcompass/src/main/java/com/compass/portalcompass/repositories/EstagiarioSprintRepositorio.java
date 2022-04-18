package com.compass.portalcompass.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.entities.EstagiarioSprintId;

public interface EstagiarioSprintRepositorio extends JpaRepository<EstagiarioSprint, Long>{

	EstagiarioSprint getById(EstagiarioSprintId id);

	Optional<EstagiarioSprint> findById(EstagiarioSprintId id);

}
