package com.compass.portalcompass.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.enums.TipoBolsa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDeEstudoForm {
	@NotEmpty(message = "Precisa preencher o campo")
	private String url;
	//Obs.: o form não tem o campo 'Tema', porque a vinculação do material com o tema será feita separadamente
}
