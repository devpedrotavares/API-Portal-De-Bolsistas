package com.compass.portalcompass.dto;

import java.util.List;

import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.entities.Tema;
import com.compass.portalcompass.enums.TipoBolsa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDeEstudoDTO {
	private Long id;
	private String url;
	private Tema tema;
}
