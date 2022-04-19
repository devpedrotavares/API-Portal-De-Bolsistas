package com.compass.portalcompass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.portalcompass.entities.Sprint;
import com.compass.portalcompass.entities.Tema;

@Repository
public interface SprintRepositorio extends JpaRepository< Sprint ,Long>{

	Sprint findByTemas(Tema tema);

}