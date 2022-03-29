package com.compass.portalcompass.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class EstagiarioSprint {
	
	@EmbeddedId
	private EstagiarioSprintId id = new EstagiarioSprintId();
	
	@ManyToOne
	@MapsId("estagiarioId")
	private Estagiario estagiario;
	
	@ManyToOne
	@MapsId("sprintId")
	private Sprint sprint;
	
	private BigDecimal notaTecnica;
	private BigDecimal notaComportamental;
	
	@ManyToMany
	//As linhas comentadas abaixo serão necessárias quando criarmos o banco de dados
	//@JoinTable(name = "estagiarioSprint_temas", joinColumns = {
		//	@JoinColumn(name = "fk_estagiarioSprint") }, inverseJoinColumns = { @JoinColumn(name = "fk_temas") })
	private Set<Tema> temasReforco;
}
