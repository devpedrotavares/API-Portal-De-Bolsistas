package com.compass.portalcompass.dto;

import java.util.List;



import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.enums.TipoBolsa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstagiarioDTO {
	private Long matricula;
	private String nome;
	private String email;
	private TipoBolsa tipoBolsa;
}
