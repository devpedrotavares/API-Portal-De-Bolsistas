package com.compass.portalcompass.services;

import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;
import com.compass.portalcompass.dto.VinculoMaterialTemaForm;
import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.exception.BancoDeDadosExcecao;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.MaterialDeEstudoRepositorio;
import com.compass.portalcompass.repositories.TemaRepositorio;

@Service
public class MaterialDeEstudoServiceImp implements MaterialDeEstudoService{

	@Autowired
	MaterialDeEstudoRepositorio repositorio;
	
	@Autowired
	TemaRepositorio temaRepositorio;
	
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

	@Override
	public Page<MaterialDeEstudoDTO> findAll(int size, int page, String sort) {
		Pageable pageable;
		if(sort == null) 
			pageable = PageRequest.of(page, size);
		else 
			pageable = PageRequest.of(page, size);
		
		Page<MaterialDeEstudo> listaMatEstudo = repositorio.findAll(pageable);
		return listaMatEstudo.map(matEst -> mapper.map(matEst, MaterialDeEstudoDTO.class));
	}

	@Override
	public MaterialDeEstudoDTO findById(Long id) {
		MaterialDeEstudo matEst = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));

		return mapper.map(matEst, MaterialDeEstudoDTO.class);
	}

	@Override
	public MaterialDeEstudoDTO update(Long id, MaterialDeEstudoForm materialDeEstudoBody) {
		MaterialDeEstudo matEst = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));
		matEst.setUrl(materialDeEstudoBody.getUrl());
		
		return mapper.map(matEst, MaterialDeEstudoDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			MaterialDeEstudo materialDeEstudo = repositorio.findById(id)
					.orElseThrow(() -> new NaoEncontradoExcecao(id));
			repositorio.delete(materialDeEstudo);
		} catch (EmptyResultDataAccessException e) {
			throw new NaoEncontradoExcecao(id);
		} catch (BancoDeDadosExcecao e) {
			throw new BancoDeDadosExcecao(e.getMessage());
		}
	}

	@Override
	public void vincularATema(VinculoMaterialTemaForm form) {
		MaterialDeEstudo materialDeEstudo = repositorio.findById(form.getMaterialDeEstudoId())
				.orElseThrow(() -> new NaoEncontradoExcecao(form.getMaterialDeEstudoId()));
		Tema tema = temaRepositorio.findById(form.getTemaId())
				.orElseThrow(() -> new NaoEncontradoExcecao(form.getTemaId()));
	
		tema.getMateriaisDeEstudo().add(materialDeEstudo);
		materialDeEstudo.setTema(tema);
	}
}