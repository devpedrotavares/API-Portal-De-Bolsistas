package com.compass.portalcompass.services;

import org.springframework.data.domain.Page;

import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.dto.TemaFormDTO;

public interface TemaService {
	
	TemaDTO insert(TemaFormDTO temaBody);
	
	Page<TemaDTO> findAll(int size, int page, String sort);

	TemaDTO findById(Long id);

	TemaDTO update(Long id, TemaFormDTO estagiarioBody);

	void delete(Long id);
}
