package com.compass.portalcompass.dto;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SprintDTO {
	private Long id;
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeInicio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeTermino;
}
