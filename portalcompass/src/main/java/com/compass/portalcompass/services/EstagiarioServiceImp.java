package com.compass.portalcompass.services;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.repositories.EstagiarioRepositorio;

@Service
public class EstagiarioServiceImp implements EstagiarioService {

	@Autowired
	private EstagiarioRepositorio repositorio;
	
	@Autowired 
	private ModelMapper mapper;

	@Override
	public EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody) {
		Estagiario estagiario = repositorio.save(mapper.map(estagiarioBody, Estagiario.class));
		return mapper.map(estagiario, EstagiarioDTO.class);
	}
	
	
}
