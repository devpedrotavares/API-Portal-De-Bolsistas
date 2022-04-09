package com.compass.portalcompass.services;



import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.dto.EstagiarioSprintDTO;
import com.compass.portalcompass.dto.VinculoEstagiarioSprintForm;
import com.compass.portalcompass.dto.VinculoInfosForm;
import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.entities.EstagiarioSprintId;
import com.compass.portalcompass.entities.Sprint;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.enums.TipoBolsa;
import com.compass.portalcompass.exception.BancoDeDadosExcecao;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.feignclients.EmailFeignClient;
import com.compass.portalcompass.feignclients.request.EmailDTO;
import com.compass.portalcompass.feignclients.response.Email;
import com.compass.portalcompass.repositories.EstagiarioRepositorio;
import com.compass.portalcompass.repositories.EstagiarioSprintRepositorio;
import com.compass.portalcompass.repositories.SprintRepositorio;
import com.compass.portalcompass.repositories.TemaRepositorio;

@Service
public class EstagiarioServiceImp implements EstagiarioService {

	@Autowired
	private EstagiarioRepositorio repositorio;
	@Autowired 
	private EstagiarioSprintRepositorio vinculoRepositorio;
	@Autowired
	private SprintRepositorio sprintRepositorio;
	@Autowired 
	private TemaRepositorio temaRepositorio;
	@Autowired
	private EmailFeignClient emailFeignClient;
	
	@Autowired 
	private ModelMapper mapper;

	@Override
	public EstagiarioDTO insert(EstagiarioFormDTO estagiarioBody) {
		Optional<Estagiario> estagiarioID = repositorio.findById(estagiarioBody.getMatricula());
		if (!estagiarioID.isEmpty()) {
			throw new BancoDeDadosExcecao("Já existe a matricula = " + estagiarioBody.getMatricula());
		}
		Estagiario estagiario = repositorio.save(mapper.map(estagiarioBody, Estagiario.class));
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
	
	@Override
	public EstagiarioDTO findById(Long id) {
		Estagiario estagiario = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));
		return mapper.map(estagiario, EstagiarioDTO.class);
	}

	@Override
	public EstagiarioDTO update(Long id, EstagiarioFormDTO estagiarioBody) {
		Estagiario estagiario = repositorio.findById(id)
				.orElseThrow(() -> new NaoEncontradoExcecao(id));
		estagiario.setNome(estagiarioBody.getNome());
		estagiario.setEmail(estagiarioBody.getEmail());
		estagiario.setTipoBolsa(estagiarioBody.getTipoBolsa());
		Estagiario update = repositorio.save(estagiario);
		return mapper.map(update, EstagiarioDTO.class);
	}
	
	@Override
	public EstagiarioSprint vincularASprint(VinculoEstagiarioSprintForm form) {
		EstagiarioSprint vinculo = new EstagiarioSprint();
		Estagiario estagiario = repositorio.getById(form.getEstagiarioId());
		Sprint sprint = sprintRepositorio.getById(form.getSprintId());
		
		vinculo.setEstagiario(estagiario);
		vinculo.setSprint(sprint);
		
		return vinculoRepositorio.save(vinculo);
	}

	@Override
	public void delete(Long id) {
		try {
			Estagiario estagiario = repositorio.findById(id)
					.orElseThrow(() -> new NaoEncontradoExcecao(id));
			repositorio.delete(estagiario);
		} catch (EmptyResultDataAccessException e) {
			throw new NaoEncontradoExcecao(id);
		} catch (BancoDeDadosExcecao e) {
			throw new BancoDeDadosExcecao(e.getMessage());
		}
	}

	@Override
	public Page<EstagiarioDTO> findByTipoBolsa(TipoBolsa tipoBolsa, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Estagiario> estagiarios = repositorio.findByTipoBolsa(tipoBolsa, pageable);
		return estagiarios.map(e -> mapper.map(e, EstagiarioDTO.class));
	}

	@Override
	public EstagiarioSprintDTO getEstagiarioSprint(Long idEstagiario, Long idSprint) {
		EstagiarioSprintId id = new EstagiarioSprintId(idEstagiario, idSprint);
		EstagiarioSprint vinculo = vinculoRepositorio.getById(id);
		return mapper.map(vinculo, EstagiarioSprintDTO.class);
	}

	//cadastra informações da relação estagiário-sprint
	@Override
	public EstagiarioSprintDTO cadastrarInfos(Long idEstagiario, Long idSprint, VinculoInfosForm form) {
		EstagiarioSprintId id = new EstagiarioSprintId(idEstagiario, idSprint);
		EstagiarioSprint vinculo = vinculoRepositorio.getById(id);
		vinculo.setNotaTecnica(form.getNotaTecnica());
		vinculo.setNotaComportamental(form.getNotaComportamental());
		
		Set<Tema> temasReforco = vinculo.getTemasReforco();
		
		form.getIdsTemasReforco().forEach(idTema -> {
			temasReforco.add(temaRepositorio.getById(idTema));
		});
		
		return mapper.map(vinculoRepositorio.save(vinculo), EstagiarioSprintDTO.class);
	}

	@Override
	public Email sendEmail(EmailDTO emailBody ) {
		Email email = emailFeignClient.sendEmail(emailBody).getBody();
		return email;
	}

}