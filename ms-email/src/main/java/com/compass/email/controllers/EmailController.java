package com.compass.email.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.email.dto.EmailDTO;
import com.compass.email.entities.Email;
import com.compass.email.services.EmailService;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
		Email email = new Email();
		BeanUtils.copyProperties(emailDTO, email);
		emailService.sendEmail(email);
		return new ResponseEntity<>(email, HttpStatus.CREATED);
	}
}
