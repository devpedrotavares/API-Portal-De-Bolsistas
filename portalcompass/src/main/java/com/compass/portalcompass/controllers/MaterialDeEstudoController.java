package com.compass.portalcompass.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;
import com.compass.portalcompass.services.MaterialDeEstudoService;

@RestController
@RequestMapping(value = "/materialDeEstudo")
public class MaterialDeEstudoController {
	
	@Autowired
	MaterialDeEstudoService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<MaterialDeEstudoDTO> insert(@RequestBody MaterialDeEstudoForm materialDeEstudoBody) {
		MaterialDeEstudoDTO materialDeEstudo = service.insert(materialDeEstudoBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(materialDeEstudo);
	}
}
