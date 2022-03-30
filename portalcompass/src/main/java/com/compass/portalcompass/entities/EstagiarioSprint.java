package com.compass.portalcompass.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@JoinColumn(name = "estagiario_id")
	private Estagiario estagiario;
	
	@ManyToOne
	@MapsId("sprintId")
	@JoinColumn(name = "sprint_id")
	private Sprint sprint;
	
	private BigDecimal notaTecnica;
	private BigDecimal notaComportamental;
	
	@ManyToMany
	@JoinTable(name = "estagiarioSprint_temas", joinColumns = { @JoinColumn(name = "estagiario_id"), @JoinColumn(name = "sprint_id")}, 
	inverseJoinColumns = { @JoinColumn(name = "tema_id") })
	private Set<Tema> temasReforco;
}
