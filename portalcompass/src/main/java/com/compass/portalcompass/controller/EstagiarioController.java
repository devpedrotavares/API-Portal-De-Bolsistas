package com.compass.portalcompass.controller;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.services.EstagiarioService;

@RestController
@RequestMapping(value = "/estagiarios")
public class EstagiarioController {

	@Autowired
	private EstagiarioService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstagiarioDTO> insert(@RequestBody EstagiarioFormDTO estagiarioBody) {
		EstagiarioDTO estagiario = service.insert(estagiarioBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(estagiario);
	}
}
