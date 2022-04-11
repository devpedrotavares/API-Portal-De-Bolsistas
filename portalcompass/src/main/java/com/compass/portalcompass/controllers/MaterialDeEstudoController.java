package com.compass.portalcompass.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;
import com.compass.portalcompass.dto.VinculoMaterialTemaForm;
import com.compass.portalcompass.services.MaterialDeEstudoService;

@RestController
@RequestMapping(value = "/materialDeEstudo")
public class MaterialDeEstudoController {

	@Autowired
	MaterialDeEstudoService service;

	@GetMapping
	@Cacheable (value = "listaTodosOsMateraisDeEstudos")
	public Page<MaterialDeEstudoDTO> findAll(@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String sort) {
		return service.findAll(size, page, sort);
	}

	@GetMapping(value = "/{id}")
	@Cacheable (value = "listaPorIdOsMateraisDeEstudos")
	public ResponseEntity<MaterialDeEstudoDTO> findById(@PathVariable Long id) {
		MaterialDeEstudoDTO materialDeEstudo = service.findById(id);
		return ResponseEntity.ok(materialDeEstudo);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<MaterialDeEstudoDTO> update(@PathVariable Long id,
			@RequestBody @Valid MaterialDeEstudoForm materialDeEstudoBody) {
		MaterialDeEstudoDTO materialDeEstudoDto = service.update(id, materialDeEstudoBody);
		return ResponseEntity.ok(materialDeEstudoDto);
	}

	// vincula o material de estudo ao tema
	@PutMapping(value = "/tema")
	@Transactional
	public ResponseEntity<?> vincularATema(@RequestBody @Valid VinculoMaterialTemaForm form) {
		service.vincularATema(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<MaterialDeEstudoDTO> insert(@RequestBody @Valid MaterialDeEstudoForm materialDeEstudoBody) {
		MaterialDeEstudoDTO materialDeEstudo = service.insert(materialDeEstudoBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(materialDeEstudo);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}