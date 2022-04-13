package com.compass.portalcompass.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.portalcompass.entities.Instrutor;

@Repository
public interface InstrutorRepositorio extends JpaRepository<Instrutor, Long> {

	Optional<Instrutor> findByEmail(String email);
}
