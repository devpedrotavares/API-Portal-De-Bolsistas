package com.compass.portalcompass.services;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;

public interface EstagiarioService {

	EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody);

	EstagiarioDTO findById(Long id);
}
