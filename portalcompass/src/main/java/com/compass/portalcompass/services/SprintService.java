package com.compass.portalcompass.services;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.compass.portalcompass.dto.SprintDTO;
import com.compass.portalcompass.dto.SprintFormDTO;
import com.compass.portalcompass.dto.TemaDTO;


public interface SprintService {
	
	SprintDTO save(SprintFormDTO body);

	Page<SprintDTO> findAll(int size, int page, String sort);
    
    SprintDTO findById(Long id);
    
    SprintDTO update(Long id, SprintFormDTO body);

    void delete(Long id);

	Page<SprintDTO> findAll(Pageable page);

	List<TemaDTO> getTemas(Long id);
	

}
