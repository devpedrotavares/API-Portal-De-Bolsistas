package com.compass.portalcompass.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.compass.portalcompass.dto.SprintDTO;
import com.compass.portalcompass.dto.SprintFormDTO;
import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.entities.Sprint;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.SprintRepositorio;
import com.compass.portalcompass.repositories.TemaRepositorio;

@SpringBootTest
class SprintServiceImpTest {
	@MockBean
	private TemaRepositorio temaRepositorio;
	
	@MockBean
	private SprintRepositorio sprintRepositorio;
	
	@Autowired
	@InjectMocks
	private SprintServiceImp sprintService;
	
	@Test
	void deveriaBuscarPorId() {
		Optional<Sprint> sprintOp = Optional.of(getSprint());
		Mockito.when(this.sprintRepositorio.findById(Mockito.anyLong())).thenReturn(sprintOp);
		SprintDTO buscado = this.sprintService.findById(Long.valueOf(1));
		Assertions.assertNotNull(buscado);
		Assertions.assertEquals(new SprintDTO(sprintOp.get()), buscado);
	}
	
	@Test
	void deveriaListarSprints() {
		List<Sprint> sprints = new ArrayList<>();
		sprints.add(getSprint());
		Page<Sprint> sprintsPage = new PageImpl<>(sprints, PageRequest.of(0, 10), 1);
		
		Mockito.when(this.sprintRepositorio.findAll(Mockito.any(Pageable.class))).thenReturn(sprintsPage);
		
		Page<SprintDTO> retornado = sprintService.findAll(10, 0, null);
		
		Assertions.assertEquals(retornado, sprintsPage.map(s -> new SprintDTO(s)));
		Assertions.assertTrue(
				retornado.getContent().get(0)
				.equals(new SprintDTO(sprints.get(0))));
		Assertions.assertTrue(retornado.getTotalPages() == 1);
	}
	
	@Test
	void deveriaAtualizar() {
		Optional<Sprint> sprintOp = Optional.of(getSprint());
		Mockito.when(this.sprintRepositorio.save(sprintOp.get())).thenReturn(sprintOp.get());
		Mockito.when(this.sprintRepositorio.findById(Mockito.anyLong())).thenReturn(sprintOp);
		
		SprintDTO retornado = sprintService.update(Long.valueOf(1), new SprintFormDTO(sprintOp.get()));
		Assertions.assertEquals(retornado, new SprintDTO(sprintOp.get()));
	}
	
	@Test
	void passarIdInvalidoDeveriaLancarExcecao() {
		Mockito.when(this.sprintRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(
				NaoEncontradoExcecao.class, 
				() -> sprintService.findById(Long.valueOf(1))
				);
	}
	
	@Test
	void deveriaRetornarOsTemasDaSprint() {
		Sprint sprint = getSprint();
		sprint.setTemas(new ArrayList<Tema>(Arrays.asList(getTema())));
		
		Mockito.when(this.sprintRepositorio.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(sprint));
		
		List<TemaDTO> retornado = sprintService.getTemas(Long.valueOf(1));
		//convertendo lista de temas numa lista de temaDTO para comparar.
		List<TemaDTO> temas = sprint.getTemas().stream().map(t -> new TemaDTO(t)).collect(Collectors.toList());
		
		Assertions.assertEquals(retornado, temas);
	}
	
	@Test
	void deveriaRetornarASprintDoTema() {
		Sprint sprint = getSprint();
		Tema tema = getTema();
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.of(tema));
		Mockito.when(this.sprintRepositorio.findByTemas(tema)).thenReturn(sprint);
		
		SprintDTO retornado = sprintService.findSprintByIdTema(Long.valueOf(1));
		
		Assertions.assertEquals(retornado, new SprintDTO(sprint));
	}
	
	private Sprint getSprint() {
		Sprint sprint = new Sprint();
		sprint.setId(Long.valueOf(1));
		sprint.setNome("nome");
		sprint.setDataDeInicio(LocalDate.now());
		sprint.setDataDeTermino(LocalDate.now());
		return sprint;
	}
	
	private Tema getTema() {
		Tema tema = new Tema();
		tema.setId(Long.valueOf(1));
		tema.setSprint(getSprint());
		return tema;
	}
}