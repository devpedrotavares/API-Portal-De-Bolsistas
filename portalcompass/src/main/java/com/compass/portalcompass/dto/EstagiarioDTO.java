package com.compass.portalcompass.dto;

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
