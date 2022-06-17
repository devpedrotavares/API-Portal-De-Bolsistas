package com.compass.email.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.email.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
