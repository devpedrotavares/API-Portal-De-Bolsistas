package com.compass.portalcompass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.portalcompass.entities.Sprint;

@Repository
public interface SprintRepositorio extends JpaRepository< Sprint ,Long>{

	Sprint findByTemas(Sprint tema);

}