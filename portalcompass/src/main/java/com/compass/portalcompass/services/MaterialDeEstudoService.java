package com.compass.portalcompass.services;



import org.springframework.data.domain.Page;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;


public interface MaterialDeEstudoService {

	MaterialDeEstudoDTO insert(MaterialDeEstudoForm materialDeEstudoBody);

	Page<MaterialDeEstudoDTO> findAll(int size, int page, String sort);

}
