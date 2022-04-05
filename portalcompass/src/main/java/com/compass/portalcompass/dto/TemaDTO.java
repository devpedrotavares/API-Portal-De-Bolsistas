package com.compass.portalcompass.dto;

import com.compass.portalcompass.entities.Tema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaDTO {
	private Long id;
	private String nome;
	private SprintDTO sprint;

	public TemaDTO(Tema tema) {
		this.id = tema.getId();
		this.nome = tema.getNome();
		this.sprint = new SprintDTO(tema.getSprint());
	}
}
