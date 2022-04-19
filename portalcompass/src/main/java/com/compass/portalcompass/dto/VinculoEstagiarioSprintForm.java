package com.compass.portalcompass.dto;



import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VinculoEstagiarioSprintForm {
	@NotNull
	private Long estagiarioId;
	@NotNull
	private Long sprintId;
}