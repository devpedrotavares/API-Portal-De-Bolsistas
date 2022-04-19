package com.compass.portalcompass.services;



import org.springframework.data.domain.Page;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;
import com.compass.portalcompass.dto.VinculoMaterialTemaForm;


public interface MaterialDeEstudoService {

	MaterialDeEstudoDTO insert(MaterialDeEstudoForm materialDeEstudoBody);

	Page<MaterialDeEstudoDTO> findAll(int size, int page, String sort);

	MaterialDeEstudoDTO findById(Long id);

	MaterialDeEstudoDTO update(Long id, MaterialDeEstudoForm materialDeEstudoBody);

	void delete(Long id);

	void vincularATema(VinculoMaterialTemaForm form);
}
