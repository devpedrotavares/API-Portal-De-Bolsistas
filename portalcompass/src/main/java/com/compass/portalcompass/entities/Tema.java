package com.compass.portalcompass.entities;

import java.util.ArrayList;
import java.util.List;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Tema {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "sprint_id")
	@JsonIgnore
	private Sprint sprint;
	@OneToMany(mappedBy = "tema", fetch = FetchType.LAZY)
	private List<MaterialDeEstudo> materiaisDeEstudo = new ArrayList<>();

}
