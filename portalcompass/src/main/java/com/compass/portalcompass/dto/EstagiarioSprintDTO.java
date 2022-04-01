package com.compass.portalcompass.dto;

import java.math.BigDecimal;
import java.util.Set;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstagiarioSprintDTO {
	private BigDecimal notaTecnica;
	private BigDecimal notaComportamental;
	private Set<TemaDTO> temasReforco;
}
