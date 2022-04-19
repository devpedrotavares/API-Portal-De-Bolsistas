package com.compass.portalcompass.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VinculoTemaSprintForm {
	@NotNull
	private Long sprintId;
	@NotNull
	private Long temaId;
}
