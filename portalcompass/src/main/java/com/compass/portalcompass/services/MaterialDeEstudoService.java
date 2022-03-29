package com.compass.portalcompass.services;

import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;


public interface MaterialDeEstudoService {

	MaterialDeEstudoDTO insert(MaterialDeEstudoForm materialDeEstudoBody);

}
