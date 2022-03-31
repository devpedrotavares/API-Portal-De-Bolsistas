package com.compass.portalcompass.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VinculoNotasForm {
	private BigDecimal notaTecnica;
	private BigDecimal notaComportamental;
}
