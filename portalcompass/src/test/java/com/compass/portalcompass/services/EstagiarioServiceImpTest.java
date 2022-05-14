package com.compass.portalcompass.services;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.compass.portalcompass.dto.EstagiarioDTO;
import com.compass.portalcompass.dto.EstagiarioFormDTO;
import com.compass.portalcompass.dto.EstagiarioSprintDTO;
import com.compass.portalcompass.dto.VinculoEstagiarioSprintForm;
import com.compass.portalcompass.dto.VinculoInfosForm;
import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.entities.EstagiarioSprintId;
import com.compass.portalcompass.entities.Sprint;
import com.compass.portalcompass.enums.TipoBolsa;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;
import com.compass.portalcompass.repositories.EstagiarioRepositorio;
import com.compass.portalcompass.repositories.EstagiarioSprintRepositorio;
import com.compass.portalcompass.repositories.SprintRepositorio;

@SpringBootTest
class EstagiarioServiceImpTest {
	@MockBean
	private EstagiarioRepositorio estagiarioRepositorio;

	@MockBean
	private SprintRepositorio sprintRepositorio;

	@MockBean
	private EstagiarioSprintRepositorio vinculoRepositorio;

	@InjectMocks
	@Autowired
	private EstagiarioServiceImp estagiarioService;

	@Test
	void deveriaBuscarPorId() {
		Optional<Estagiario> estagiarioOp = Optional.of(getEstagiario());
		Mockito.when(this.estagiarioRepositorio.findById(Mockito.anyLong())).thenReturn(estagiarioOp);
		EstagiarioDTO buscado = this.estagiarioService.findById(Long.valueOf(1));
		Assertions.assertNotNull(buscado);
		Assertions.assertEquals(new EstagiarioDTO(estagiarioOp.get()), buscado);
	}

	@Test
	void deveriaListarEstagiarios() {
		List<Estagiario> estagiarios = new ArrayList<>();
		estagiarios.add(getEstagiario());
		Page<Estagiario> estagiariosPage = new PageImpl<>(estagiarios, PageRequest.of(0, 10), 1);

		Mockito.when(this.estagiarioRepositorio.findAll(Mockito.any(Pageable.class))).thenReturn(estagiariosPage);

		Page<EstagiarioDTO> retornado = estagiarioService.findAll(10, 0, null);

		Assertions.assertEquals(retornado, estagiariosPage.map(est -> new EstagiarioDTO(est)));
		Assertions.assertEquals(retornado.getContent().get(0), new EstagiarioDTO(estagiarios.get(0)));
		Assertions.assertTrue(retornado.getTotalPages() == 1);
	}

	@Test
	void deveriaListarPorTipoBolsa() {
		List<Estagiario> estagiarios = new ArrayList<>();
		estagiarios.add(getEstagiario());
		Page<Estagiario> estagiariosPage = new PageImpl<>(estagiarios, PageRequest.of(0, 10), 1);

		Mockito.when(this.estagiarioRepositorio.findByTipoBolsa(TipoBolsa.SPRING_BOOT, PageRequest.of(0, 10)))
				.thenReturn(estagiariosPage);
		
		Page<EstagiarioDTO> retornado = estagiarioService.findByTipoBolsa(TipoBolsa.SPRING_BOOT, 10, 0);
		
		Assertions.assertEquals(retornado, estagiariosPage.map(est -> new EstagiarioDTO(est)));
	}

	@Test
	void deveriaAtualizar() {
		Optional<Estagiario> estagiarioOp = Optional.of(getEstagiario());
		Mockito.when(this.estagiarioRepositorio.findById(Mockito.anyLong())).thenReturn(estagiarioOp);
		Mockito.when(this.estagiarioRepositorio.save(estagiarioOp.get())).thenReturn(estagiarioOp.get());

		EstagiarioDTO retornado = estagiarioService.update(Long.valueOf(1), new EstagiarioFormDTO(estagiarioOp.get()));
		Assertions.assertEquals(retornado, new EstagiarioDTO(estagiarioOp.get()));
	}

	@Test
	void passarIdInvalidoDeveriaLancarExcecao() {
		Mockito.when(this.estagiarioRepositorio.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(NaoEncontradoExcecao.class, () -> estagiarioService.findById(Long.valueOf(1)));
	}

	@Test
	void deveriaVincularASprint() {
		VinculoEstagiarioSprintForm form = new VinculoEstagiarioSprintForm();
		form.setEstagiarioId(Long.valueOf(1));
		form.setSprintId(Long.valueOf(1));

		EstagiarioSprint vinculo = new EstagiarioSprint();
		vinculo.setEstagiario(getEstagiario());
		vinculo.setSprint(getSprint());

		Mockito.when(this.estagiarioRepositorio.getById(Mockito.anyLong())).thenReturn(getEstagiario());
		Mockito.when(this.sprintRepositorio.getById(Mockito.anyLong())).thenReturn(getSprint());
		Mockito.when(this.vinculoRepositorio.save(vinculo)).thenReturn(vinculo);

		EstagiarioSprint vinculoRetornado = this.estagiarioService.vincularASprint(form);

		Assertions.assertEquals(vinculoRetornado, vinculo);
	}

	@Test
	void deveriaRetornarEstagiarioSprint() {
		// definindo vínculo
		EstagiarioSprint vinculo = new EstagiarioSprint();
		vinculo.setEstagiario(getEstagiario());
		vinculo.setSprint(getSprint());
		vinculo.setNotaTecnica(new BigDecimal("9.5"));

		Mockito.when(this.vinculoRepositorio.findById(new EstagiarioSprintId(Long.valueOf(1), Long.valueOf(1))))
		.thenReturn(Optional.of(vinculo));

		EstagiarioSprintDTO retornado = this.estagiarioService.getEstagiarioSprint(Long.valueOf(1), Long.valueOf(1));

		Assertions.assertEquals(new EstagiarioSprintDTO(vinculo), retornado);
	}

	@Test
	void deveriaCadastrarInfosEmEstagiarioSprint() {
		// definindo vínculo
		EstagiarioSprint vinculo = new EstagiarioSprint();
		vinculo.setEstagiario(getEstagiario());
		vinculo.setSprint(getSprint());
		vinculo.setNotaTecnica(new BigDecimal("8.0"));
		vinculo.setNotaComportamental(new BigDecimal("8.0"));

		// definindo vinculoForm
		VinculoInfosForm form = new VinculoInfosForm(new BigDecimal("9.5"), new BigDecimal("9.2"),
				new ArrayList<Long>());

		Mockito.when(this.vinculoRepositorio.findById(new EstagiarioSprintId(Long.valueOf(1), Long.valueOf(1))))
				.thenReturn(Optional.of(vinculo));
		Mockito.when(this.vinculoRepositorio.save(Mockito.any(EstagiarioSprint.class))).then(returnsFirstArg());

		EstagiarioSprintDTO retornado = this.estagiarioService.cadastrarInfos(Long.valueOf(1), Long.valueOf(1), form);

		Assertions.assertEquals(retornado, new EstagiarioSprintDTO(vinculo));
	}

	private Estagiario getEstagiario() {
		Estagiario estagiario = new Estagiario();
		estagiario.setMatricula(Long.valueOf(1));
		estagiario.setNome("nome");
		estagiario.setEmail("email@gmail.com");
		estagiario.setTipoBolsa(TipoBolsa.SPRING_BOOT);
		return estagiario;
	}

	private Sprint getSprint() {
		Sprint sprint = new Sprint();
		sprint.setId(Long.valueOf(1));
		sprint.setNome("nome");
		sprint.setDataDeInicio(LocalDate.now());
		sprint.setDataDeTermino(LocalDate.now());
		return sprint;
	}
}
