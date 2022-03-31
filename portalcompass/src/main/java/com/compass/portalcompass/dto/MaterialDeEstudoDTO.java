package com.compass.portalcompass.dto;



import com.compass.portalcompass.entities.Tema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDeEstudoDTO {
	private Long id;
	private String url;
	private TemaDTO tema;
}
