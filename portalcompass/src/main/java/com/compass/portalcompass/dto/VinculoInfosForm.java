package com.compass.portalcompass.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VinculoInfosForm {
	private BigDecimal notaTecnica;
	private BigDecimal notaComportamental;
	private List<Long> idsTemasReforco;
}
