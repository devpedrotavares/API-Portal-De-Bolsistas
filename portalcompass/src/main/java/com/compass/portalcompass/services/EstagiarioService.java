package com.compass.portalcompass.services;

import org.springframework.data.domain.Page;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.dto.EstagiarioSprintDTO;
import com.compass.portalcompass.dto.VinculoEstagiarioSprintForm;
import com.compass.portalcompass.dto.VinculoInfosForm;
import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.enums.TipoBolsa;
import com.compass.portalcompass.feignclients.request.EmailDTO;
import com.compass.portalcompass.feignclients.response.Email;

public interface EstagiarioService {

	EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody);
	
	Page<EstagiarioDTO> findAll(int size, int page, String sort);

	EstagiarioDTO findById(Long id);
	
	Email sendEmail(EmailDTO emailBody);

	EstagiarioDTO update(Long id, EstagiarioFormDTO estagiarioBody);

	void delete(Long id);

	Page<EstagiarioDTO> findByTipoBolsa(TipoBolsa tipoBolsa, int size, int page);

	EstagiarioSprint vincularASprint(VinculoEstagiarioSprintForm form);

	EstagiarioSprintDTO getEstagiarioSprint(Long idEstagiario, Long idSprint);

	EstagiarioSprintDTO cadastrarInfos(Long idEstagiario, Long idSprint, VinculoInfosForm form);
}