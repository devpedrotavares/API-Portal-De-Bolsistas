package com.compass.portalcompass.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.dto.TemaFormDTO;
import com.compass.portalcompass.dto.VinculoTemaSprintForm;
import com.compass.portalcompass.services.TemaService;

@RestController
@RequestMapping(value = "/temas")
public class TemaController {

	@Autowired
	private TemaService service;

	@PostMapping
	@Transactional
	public ResponseEntity<TemaDTO> insert(@RequestBody @Valid TemaFormDTO temaBody) {
		TemaDTO tema = service.insert(temaBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(tema);
	}

	@GetMapping
	public Page<TemaDTO> findAll(@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String sort) {
		return service.findAll(size, page, sort);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TemaDTO> findById(@PathVariable Long id) {
		TemaDTO tema = service.findById(id);
		return ResponseEntity.ok(tema);
	}	
	@GetMapping(value = "/{id}/materiaisDeEstudo")
	public List<MaterialDeEstudoDTO> findMateriaisByIdTema(@PathVariable Long id) {
		return service.findMateriaisByIdTema(id);
	}
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<TemaDTO> update(@PathVariable Long id, @RequestBody @Valid TemaFormDTO temaBody) {
		TemaDTO tema = service.update(id, temaBody);
		return ResponseEntity.ok(tema);
	}

	// vincula o material de estudo ao tema
	@PutMapping(value = "/sprint")
	@Transactional
	public ResponseEntity<?> vincularASprint(@RequestBody @Valid VinculoTemaSprintForm form) {
		service.vincularSprint(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
