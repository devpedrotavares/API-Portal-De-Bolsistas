package com.compass.portalcompass.dto;

import java.time.LocalDate;

import com.compass.portalcompass.entities.Sprint;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SprintDTO {
	private Long id;
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeInicio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeTermino;
	
	public SprintDTO(Sprint sprint) {
		this.id = sprint.getId();
		this.nome = sprint.getNome();
		this.dataDeInicio = sprint.getDataDeInicio();
		this.dataDeTermino = sprint.getDataDeTermino();
	}
}