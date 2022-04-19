package com.compass.portalcompass.services;
import java.util.List;
import org.springframework.data.domain.Page;

import com.compass.portalcompass.dto.SprintDTO;
import com.compass.portalcompass.dto.SprintFormDTO;
import com.compass.portalcompass.dto.TemaDTO;


public interface SprintService {
	
	SprintDTO insert(SprintFormDTO body);

	Page<SprintDTO> findAll(int size, int page, String sort);
    
    SprintDTO findById(Long id);
    
    SprintDTO update(Long id, SprintFormDTO body);

    void delete(Long id);

	SprintDTO findSprintByIdTema(Long id);

	List<TemaDTO> getTemas(Long id);

	

}
