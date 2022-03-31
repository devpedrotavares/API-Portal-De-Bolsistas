package com.compass.portalcompass.dto;

import java.util.List;

import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.entities.Sprint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VinculoMaterialTemaForm {
	private Long materialDeEstudoId;
	private Long temaId;
}
