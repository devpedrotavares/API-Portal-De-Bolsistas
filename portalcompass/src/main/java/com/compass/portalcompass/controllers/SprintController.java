package com.compass.portalcompass.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compass.portalcompass.dto.SprintDTO;
import com.compass.portalcompass.dto.SprintFormDTO;
import com.compass.portalcompass.services.SprintService;



@RestController
@RequestMapping(value = "/sprint")
public class SprintController {
		
		@Autowired
	    private SprintService service;

	    @PostMapping
	    @Transactional
	    public ResponseEntity<SprintDTO> save(@RequestBody @Valid SprintFormDTO SprintBody) {
	        SprintDTO sprint = this.service.save(SprintBody);
	        return ResponseEntity.status(HttpStatus.CREATED).body(sprint);
	    }
	     
	    
	    @GetMapping(value = "/{id}")
		public ResponseEntity<SprintDTO> findById(@PathVariable Long id) {
			SprintDTO sprint = service.findById(id);
			return ResponseEntity.ok(sprint);
	    }
	    
	    @PutMapping(value = "/{id}")
		@Transactional
		public ResponseEntity<SprintDTO> update(@PathVariable Long id, @RequestBody SprintFormDTO Body) {
			SprintDTO sprint = service.update(id, Body);
			return ResponseEntity.ok(sprint);
		}
			
	    @DeleteMapping(path = "/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        this.service.delete(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
}
	

