package com.compass.portalcompass.dto;

import java.util.List;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.enums.TipoBolsa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstagiarioFormDTO {

	@NotEmpty(message = "Precisa preencher o campo")
	private Long matricula;
	@NotEmpty(message = "Precisa preencher o campo")
	private String nome;
	@NotEmpty(message = "Precisa preencher um email")
	private String email;
	@NotNull(message = "Precisa preencher com o tipo da bolsa")
	private TipoBolsa tipoBolsa;
}
