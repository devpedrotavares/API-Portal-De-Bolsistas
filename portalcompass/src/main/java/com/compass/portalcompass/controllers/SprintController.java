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

import com.compass.portalcompass.dto.SprintDTO;
import com.compass.portalcompass.dto.SprintFormDTO;
import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.services.SprintService;



@RestController
@RequestMapping(value = "/sprint")
public class SprintController {
		
		@Autowired
	    private SprintService service;

	    @PostMapping
	    @Transactional
	    public ResponseEntity<SprintDTO> insert(@RequestBody @Valid SprintFormDTO SprintBody) {
	        SprintDTO sprint = this.service.insert(SprintBody);
	        return ResponseEntity.status(HttpStatus.CREATED).body(sprint);
	    }
	    
	    @GetMapping
		public Page<SprintDTO> findAll(@RequestParam(defaultValue = "10") int size, 
				@RequestParam(defaultValue = "0") int page, 
				@RequestParam(required = false) String sort) {
			return service.findAll(size, page, sort);
		}
		
	     
	    
	    @GetMapping(value = "/{id}")
		public ResponseEntity<SprintDTO> findById(@PathVariable Long id) {
			SprintDTO sprint = service.findById(id);
			return ResponseEntity.ok(sprint);
	    }
	    
	    @GetMapping(value = "/{id}/temas")
	    public List<TemaDTO> getTemas(@PathVariable Long id){
	    	return service.getTemas(id);
	    }
	    
	    @PutMapping(value = "/{id}")
		@Transactional
		public ResponseEntity<SprintDTO> update(@PathVariable Long id, @RequestBody @Valid SprintFormDTO Body) {
			SprintDTO sprint = service.update(id, Body);
			return ResponseEntity.ok(sprint);
		}
			
	    @DeleteMapping(path = "/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        this.service.delete(id);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
	    
	    @GetMapping(value = "/{id}/tema")
		public ResponseEntity<SprintDTO> findSprintByIdTema(@PathVariable Long id) {
			SprintDTO sprint = service.findSprintByIdTema(id);
			return ResponseEntity.ok().body(sprint);	
		}
}