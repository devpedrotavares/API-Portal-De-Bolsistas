package com.compass.portalcompass.entities;

import java.util.List;

import com.compass.portalcompass.enums.TipoBolsa;

public class Estagiario {
	private Long matricula;
	private String nome;
	private String emmail;
	private TipoBolsa tipoBolsas;
	private List<EstagiarioSprints> estagiarioSprints;
}
