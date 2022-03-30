package com.compass.portalcompass.dto;

import java.util.List;

import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.entities.Sprint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaDTO {
	
	private Long id;
	private String nome;
	private Sprint sprint;
	private List<MaterialDeEstudo> materiaisDeEstudo;
}
