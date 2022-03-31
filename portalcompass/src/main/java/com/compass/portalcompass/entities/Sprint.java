package com.compass.portalcompass.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Sprint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	public void addTemas(Tema obj) {
		temas.add(obj);
	}
	

	
}
