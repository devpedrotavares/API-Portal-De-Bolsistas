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

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.TemaDTO;
import com.compass.portalcompass.dto.TemaFormDTO;
import com.compass.portalcompass.dto.VinculoTemaSprintForm;
import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.entities.Sprint;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.SprintRepositorio;
import com.compass.portalcompass.repositories.TemaRepositorio;

@SpringBootTest
class TemaServiceImpTest {
	@MockBean
	private TemaRepositorio temaRepositorio;
	
	@MockBean
	private SprintRepositorio sprintRepositorio;
	
	@InjectMocks
	@Autowired
	private TemaServiceImp temaService;
	
	@Test
	void deveriaBuscarPorId() {
		Optional<Tema> temaOp = Optional.of(getTema());
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong())).thenReturn(temaOp);
		TemaDTO buscado = this.temaService.findById(Long.valueOf(1));
		Assertions.assertNotNull(buscado);
		Assertions.assertEquals(new TemaDTO(temaOp.get()), buscado);
	}
	
	@Test
	void deveriaListarSprints() {
		List<Tema> temas = new ArrayList<>();
		temas.add(getTema());
		Page<Tema> temasPage = new PageImpl<>(temas, PageRequest.of(0, 10), 1);
		
		Mockito.when(this.temaRepositorio.findAll(Mockito.any(Pageable.class))).thenReturn(temasPage);
		
		Page<TemaDTO> retornado = temaService.findAll(10, 0, null);
		
		Assertions.assertEquals(retornado, temasPage.map(t -> new TemaDTO(t)));
		Assertions.assertTrue(
				retornado.getContent().get(0)
				.equals(new TemaDTO(temas.get(0))));
		Assertions.assertTrue(retornado.getTotalPages() == 1);
	}
	
	@Test
	void deveriaAtualizar() {
		Optional<Tema> temaOp = Optional.of(getTema());
		Mockito.when(this.temaRepositorio.save(temaOp.get())).thenReturn(temaOp.get());
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong())).thenReturn(temaOp);
		
		TemaDTO retornado = temaService.update(Long.valueOf(1), new TemaFormDTO(temaOp.get()));
		Assertions.assertEquals(retornado, new TemaDTO(temaOp.get()));
	}
	
	@Test
	void passarIdInvalidoDeveriaLancarExcecao() {
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(
				NaoEncontradoExcecao.class, 
				() -> temaService.findById(Long.valueOf(1))
				);
	}
	
	@Test
	void deveriaRetornarOsMateriaisDeEstudoDoTema() {
		Tema tema = getTema();
		MaterialDeEstudo mat = getMaterialDeEstudo();
		mat.setTema(tema);
		tema.setMateriaisDeEstudo(new ArrayList<MaterialDeEstudo>(Arrays.asList(mat)));
		
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(tema));
		
		List<MaterialDeEstudoDTO> retornado = temaService.findMateriaisByIdTema(Long.valueOf(1));
		//convertendo lista de materiais de estudo numa lista de MaterialDeEstudoDTO para comparar.
		List<MaterialDeEstudoDTO> materiaisDeEstudo = 
				tema.getMateriaisDeEstudo().stream().map(m -> new MaterialDeEstudoDTO(m)).collect(Collectors.toList());
		
		Assertions.assertEquals(retornado, materiaisDeEstudo);
	}
	
	@Test
	void deveriaVincularASprint() {
		VinculoTemaSprintForm form = new VinculoTemaSprintForm();
		form.setSprintId(Long.valueOf(1));
		form.setTemaId(Long.valueOf(1));
		
		Tema tema = getTema();
		Sprint sprint = getSprint();
		
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(tema));
		Mockito.when(this.sprintRepositorio.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(sprint));
		
		this.temaService.vincularSprint(form);
		
		Assertions.assertEquals(tema.getSprint(), sprint);
		Assertions.assertEquals(tema, sprint.getTemas().get(0));
	}
	
	Tema getTema(){
		Tema tema = new Tema();
		tema.setId(Long.valueOf(1));
		tema.setNome("Nome");
		return tema;
	}
	
	MaterialDeEstudo getMaterialDeEstudo(){
		MaterialDeEstudo mat = new MaterialDeEstudo();
		mat.setId(Long.valueOf(1));
		mat.setUrl("url");
		return mat;
	}
	
	Sprint getSprint() {
		Sprint sprint = new Sprint();
		sprint.setId(Long.valueOf(1));
		sprint.setNome("nome");
		sprint.setDataDeInicio(LocalDate.now());
		sprint.setDataDeTermino(LocalDate.now());
		return sprint;
	}

}
