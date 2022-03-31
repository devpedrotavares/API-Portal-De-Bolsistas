package com.compass.portalcompass.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.compass.portalcompass.entities.Tema;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class SprintFormDTO {
	
	@NotEmpty(message = "Precisa preencher o campo")
	private Long id;
	
	@NotBlank (message = "O campo precisa ser preenchido")
	private String nome;
	
	@NotEmpty (message = "O campo não pode ser nulo") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataDeInicio;
	
	@NotEmpty (message = "O campo não pode ser nulo")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataDeTermino;
	
	@OneToMany(mappedBy="sprint")
	private List<Tema> temas;
	
}
