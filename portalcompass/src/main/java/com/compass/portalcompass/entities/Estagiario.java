package com.compass.portalcompass.entities;

import java.util.List;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.compass.portalcompass.enums.TipoBolsa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Estagiario {
	@Id
	private Long matricula;
	private String nome;
	private String email;
	@Enumerated(EnumType.STRING)
	private TipoBolsa tipoBolsa;
	@OneToMany(mappedBy = "estagiario")
	private List<EstagiarioSprint> estagiarioSprints;
}