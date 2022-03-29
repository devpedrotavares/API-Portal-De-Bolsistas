package com.compass.portalcompass.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.EstagiarioRepositorio;

@Service
public class EstagiarioServiceImp implements EstagiarioService {

	@Autowired
	private EstagiarioRepositorio repositorio;
	
	@Autowired 
	private ModelMapper mapper;

	@Override
	public EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody) {
		Estagiario estagiario = mapper.map(estagiarioBody, Estagiario.class);
		repositorio.save(estagiario);
		return mapper.map(estagiario, EstagiarioDTO.class);
	}

	@Override
	public Page<EstagiarioDTO> findAll(int size, int page, String sort) {
		Pageable pageable;
		if(sort == null) pageable = PageRequest.of(page, size);
		else pageable = PageRequest.of(page, size, Sort.by(sort));
		
		Page<Estagiario> estagiariosPaginacao = repositorio.findAll(pageable);
		return estagiariosPaginacao.map(e -> mapper.map(e, EstagiarioDTO.class));
	}
	
	public EstagiarioDTO findById(Long id) {
		Estagiario estagiario = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));
		return mapper.map(estagiario, EstagiarioDTO.class);
	}

	@Override
	public EstagiarioDTO update(Long id, EstagiarioFormDTO form) {
		Estagiario estagiario = repositorio.getById(id);
		estagiario.setNome(form.getNome());
		estagiario.setEmail(form.getEmail());
		estagiario.setTipoBolsas(form.getTipoBolsas());
		return mapper.map(estagiario, EstagiarioDTO.class);
	}

	@Override
	public void delete(Long id) {
		repositorio.deleteById(id);
	}
}