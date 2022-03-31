package com.compass.portalcompass.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaDTO {
	private Long id;
	private String nome;
	private SprintDTO sprint;
}
