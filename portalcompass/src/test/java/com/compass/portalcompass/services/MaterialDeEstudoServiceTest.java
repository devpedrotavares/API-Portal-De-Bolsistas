package com.compass.portalcompass.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.compass.portalcompass.dto.MaterialDeEstudoDTO;
import com.compass.portalcompass.dto.MaterialDeEstudoForm;
import com.compass.portalcompass.dto.VinculoMaterialTemaForm;
import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.MaterialDeEstudoRepositorio;
import com.compass.portalcompass.repositories.TemaRepositorio;

@SpringBootTest
class MaterialDeEstudoServiceTest {
	@MockBean
	TemaRepositorio temaRepositorio;
	
	@MockBean
	MaterialDeEstudoRepositorio materialDeEstudoRepositorio;

	@Autowired
	@InjectMocks
	private MaterialDeEstudoServiceImp materialDeEstudoService;

	@Autowired
	private ModelMapper mapper;

	@Test
	void deveriaBuscarPorId() {
		Optional<MaterialDeEstudo> materialDeEstudoOp = Optional.of(getMaterialDeEstudo());
		Mockito.when(this.materialDeEstudoRepositorio.findById(Mockito.anyLong())).thenReturn(materialDeEstudoOp);

		MaterialDeEstudoDTO buscado = this.materialDeEstudoService.findById(Long.valueOf(1));
		MaterialDeEstudoDTO dto = mapper.map(getMaterialDeEstudo(), MaterialDeEstudoDTO.class);

		Assertions.assertNotNull(buscado);
		Assertions.assertEquals(dto, buscado);
	}

	@Test
	void deveriaListarMateriais() {
		List<MaterialDeEstudo> materiais = new ArrayList<>();
		materiais.add(getMaterialDeEstudo());
		Page<MaterialDeEstudo> materiaisPage = new PageImpl<>(materiais, PageRequest.of(0, 10), 1);

		Mockito.when(this.materialDeEstudoRepositorio.findAll(Mockito.any(Pageable.class))).thenReturn(materiaisPage);

		Page<MaterialDeEstudoDTO> retornado = materialDeEstudoService.findAll(10, 0, null);

		Assertions.assertEquals(retornado, materiaisPage.map(s -> mapper.map(s, MaterialDeEstudoDTO.class)));
		Assertions.assertEquals(
				retornado.getContent().get(0), mapper.map(materiais.get(0), MaterialDeEstudoDTO.class));
		Assertions.assertTrue(retornado.getTotalPages() == 1);
	}

	@Test
	void deveriaAtualizar() {
		Optional<MaterialDeEstudo> materialDeEstudoOp = Optional.of(getMaterialDeEstudo());
		Mockito.when(this.materialDeEstudoRepositorio.save(materialDeEstudoOp.get()))
				.thenReturn(materialDeEstudoOp.get());
		Mockito.when(this.materialDeEstudoRepositorio.findById(Mockito.anyLong())).thenReturn(materialDeEstudoOp);

		MaterialDeEstudoDTO retornado = materialDeEstudoService.update(Long.valueOf(1),
				new MaterialDeEstudoForm(materialDeEstudoOp.get()));
		Assertions.assertEquals(retornado, mapper.map(materialDeEstudoOp.get(), MaterialDeEstudoDTO.class));
	}
	
	@Test
	void passarIdInvalidoDeveriaLancarExcecao() {
		Mockito.when(this.materialDeEstudoRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(
				NaoEncontradoExcecao.class, 
				() -> materialDeEstudoService.findById(Long.valueOf(1))
				);
	}

	@Test
	void deveriaVincularATema() {
		VinculoMaterialTemaForm form = new VinculoMaterialTemaForm();
		form.setMaterialDeEstudoId(Long.valueOf(1));
		form.setTemaId(Long.valueOf(1));
		
		MaterialDeEstudo mat = getMaterialDeEstudo();
		Tema tema = getTema();
		
		Mockito.when(this.materialDeEstudoRepositorio.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(mat));
		Mockito.when(this.temaRepositorio.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(tema));
		
		this.materialDeEstudoService.vincularATema(form);
		
		Assertions.assertEquals(mat.getTema(), tema);
		Assertions.assertEquals(mat, tema.getMateriaisDeEstudo().get(0));
	}

	private MaterialDeEstudo getMaterialDeEstudo() {
		MaterialDeEstudo mat = new MaterialDeEstudo();
		mat.setId(Long.valueOf(1));
		mat.setUrl("url");
		mat.setTema(null);
		return mat;
	}
	
	private Tema getTema(){
		Tema tema = new Tema();
		tema.setId(Long.valueOf(1));
		tema.setNome("Nome");
		return tema;
	}
}
