package com.compass.portalcompass.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.compass.portalcompass.entities.EstagiarioSprint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstagiarioSprintDTO {
	private BigDecimal notaTecnica;
	private BigDecimal notaComportamental;
	private Set<TemaDTO> temasReforco;
	
	public EstagiarioSprintDTO(EstagiarioSprint estagiarioSprint) {
		this.notaTecnica = estagiarioSprint.getNotaTecnica();
		this.notaComportamental = estagiarioSprint.getNotaComportamental();
		if(estagiarioSprint.getTemasReforco() != null)
			this.temasReforco = estagiarioSprint.getTemasReforco().stream().map(tr -> new TemaDTO(tr)).collect(Collectors.toSet());
	}
}
