package com.compass.portalcompass.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.SprintDTO;
import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.dto.TemaFormDTO;
import com.compass.portalcompass.dto.VinculoTemaSprintForm;

public interface TemaService {
	
	TemaDTO insert(TemaFormDTO temaBody);
	
	Page<TemaDTO> findAll(int size, int page, String sort);

	TemaDTO findById(Long id);

	TemaDTO update(Long id, TemaFormDTO estagiarioBody);

	void delete(Long id);
	
	void vincularSprint(VinculoTemaSprintForm form);
	
	SprintDTO findSprintByIdTema(Long id);

	List<MaterialDeEstudoDTO> findMateriaisByIdTema(Long id);

}
