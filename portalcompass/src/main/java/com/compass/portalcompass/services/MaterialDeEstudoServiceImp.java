package com.compass.portalcompass.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;
import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.exception.BancoDeDadosExcecao;
import com.compass.portalcompass.repositories.MaterialDeEstudoRepositorio;

@Service
public class MaterialDeEstudoServiceImp implements MaterialDeEstudoService{

	@Autowired
	MaterialDeEstudoRepositorio repositorio;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public MaterialDeEstudoDTO insert(MaterialDeEstudoForm materialDeEstudoBody) {
		Optional<MaterialDeEstudo> materialDeEstudo = repositorio.findByUrl(materialDeEstudoBody.getUrl());
		if(materialDeEstudo.isEmpty() == false) 
			throw new BancoDeDadosExcecao("Já existe material com a url = " + materialDeEstudoBody.getUrl());
		
		MaterialDeEstudo criado = repositorio.save(mapper.map(materialDeEstudoBody, MaterialDeEstudo.class));
		return mapper.map(criado, MaterialDeEstudoDTO.class);
	}
	
}
