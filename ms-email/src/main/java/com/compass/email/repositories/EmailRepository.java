package com.compass.email.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compass.email.entities.Email;

@Transactional
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
