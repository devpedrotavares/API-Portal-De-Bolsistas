package com.compass.portalcompass.services;

import java.util.List;

import org.springframework.data.domain.Page;


import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.enums.TipoBolsa;

public interface EstagiarioService {

	EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody);

	Page<EstagiarioDTO> findAll(int size, int page, String sort);

	EstagiarioDTO findById(Long id);

	EstagiarioDTO update(Long id, EstagiarioFormDTO estagiarioBody);

	void delete(Long id);

	List<EstagiarioDTO> findByTipoBolsa(TipoBolsa tipoBolsa);
}