package com.compass.portalcompass.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VinculoMaterialTemaForm {
	@NotNull
	private Long materialDeEstudoId;
	@NotNull
	private Long temaId;
	
}
