package com.compass.portalcompass.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.dto.TemaFormDTO;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.exception.BancoDeDadosExcecao;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.TemaRepositorio;

@Service
public class TemaServiceImp implements TemaService {

	@Autowired
	private TemaRepositorio repositorio;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public TemaDTO insert(TemaFormDTO temaBody) {
		Tema tema = repositorio.save(mapper.map(temaBody, Tema.class));
		return mapper.map(tema, TemaDTO.class);
	}

	@Override
	public Page<TemaDTO> findAll(int size, int page, String sort) {
		Pageable pageable;
		if(sort == null) pageable = PageRequest.of(page, size);
		else pageable = PageRequest.of(page, size, Sort.by(sort));
		
		Page<Tema> temaPaginacao = repositorio.findAll(pageable);
		return temaPaginacao.map(e -> mapper.map(e, TemaDTO.class));
	}

	@Override
	public TemaDTO findById(Long id) {
		Tema tema = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));
		return mapper.map(tema, TemaDTO.class);
	}

	@Override
	public TemaDTO update(Long id, TemaFormDTO estagiarioBody) {
		Tema tema = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));
		tema.setNome(estagiarioBody.getNome());
		Tema update = repositorio.save(tema);
		return mapper.map(update, TemaDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			Tema tema = repositorio.findById(id)
					.orElseThrow(() -> new NaoEncontradoExcecao(id));
			repositorio.delete(tema);
		} catch (EmptyResultDataAccessException e) {
			throw new NaoEncontradoExcecao(id);
		} catch (BancoDeDadosExcecao e) {
			throw new BancoDeDadosExcecao(e.getMessage());
		}
	}

}
