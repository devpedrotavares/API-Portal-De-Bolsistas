package com.compass.portalcompass.controllers;

import javax.transaction.Transactional;

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

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.dto.EstagiarioSprintDTO;
import com.compass.portalcompass.dto.VinculoEstagiarioSprintForm;
import com.compass.portalcompass.dto.VinculoNotasForm;
import com.compass.portalcompass.enums.TipoBolsa;
import com.compass.portalcompass.services.EstagiarioService;

@RestController
@RequestMapping(value = "/estagiarios")
public class EstagiarioController {

	@Autowired
	private EstagiarioService service;

	// Retorna todos os estagiários. Obs.: tem parâmetro opcional para buscar pelo
	// tipo da bolsa
	@GetMapping
	public Page<EstagiarioDTO> findAll(@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String sort,
			@RequestParam(required = false) TipoBolsa tipoBolsa) {
		if (tipoBolsa != null)
			return service.findByTipoBolsa(tipoBolsa, size, page);
		return service.findAll(size, page, sort);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EstagiarioDTO> findById(@PathVariable Long id) {
		EstagiarioDTO estagiario = service.findById(id);
		return ResponseEntity.ok(estagiario);
	}

	@GetMapping(value = "/{idEstagiario}/sprint/{idSprint}")
	public ResponseEntity<EstagiarioSprintDTO> getEstagiarioSprint(@PathVariable Long idEstagiario,
			@PathVariable Long idSprint) {
		EstagiarioSprintDTO infos = service.getEstagiarioSprint(idEstagiario, idSprint);
		return ResponseEntity.ok(infos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EstagiarioDTO> insert(@RequestBody EstagiarioFormDTO estagiarioBody) {
		EstagiarioDTO estagiario = service.insert(estagiarioBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(estagiario);
	}

	//registra as notas do estagiário naquela sprint (na entidade associativa).
	@PostMapping(value = "/{idEstagiario}/sprint/{idSprint}/notas")
	@Transactional
	public ResponseEntity<Void> cadastraNotas(@PathVariable Long idEstagiario,
			@PathVariable Long idSprint, @RequestBody VinculoNotasForm form) {
		service.cadastrarNotas(idEstagiario, idSprint, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//adiciona um tema de reforço à relação estagiário-sprint
	@PostMapping(value = "/{idEstagiario}/sprint/{idSprint}/temaReforco")
	@Transactional
	public ResponseEntity<Void> adicinaTemaDeReforco(@PathVariable Long idEstagiario,
			@PathVariable Long idSprint, @RequestBody Long idTema) {
		service.cadastrarTema(idEstagiario, idSprint, idTema);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<EstagiarioDTO> update(@PathVariable Long id, @RequestBody EstagiarioFormDTO estagiarioBody) {
		EstagiarioDTO estagiario = service.update(id, estagiarioBody);
		return ResponseEntity.ok(estagiario);
	}

	@PutMapping(value = "/sprint")
	public ResponseEntity<?> vincularASprint(@RequestBody VinculoEstagiarioSprintForm form) {
		service.vincularASprint(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
