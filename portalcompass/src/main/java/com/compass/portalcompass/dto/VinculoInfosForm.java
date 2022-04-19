package com.compass.portalcompass.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VinculoInfosForm {
	@NotNull
	private BigDecimal notaTecnica;
	@NotNull
	private BigDecimal notaComportamental;
	private List<Long> idsTemasReforco;
}
