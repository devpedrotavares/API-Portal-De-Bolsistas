package com.compass.portalcompass.services;

import org.springframework.data.domain.Page;


import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;

public interface EstagiarioService {

	EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody);

	Page<EstagiarioDTO> findAll(int size, int page, String sort);

	EstagiarioDTO findById(Long id);
}