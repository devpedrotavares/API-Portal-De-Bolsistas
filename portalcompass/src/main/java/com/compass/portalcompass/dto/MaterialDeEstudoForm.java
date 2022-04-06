package com.compass.portalcompass.dto;



import javax.validation.constraints.NotEmpty;

import com.compass.portalcompass.entities.MaterialDeEstudo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDeEstudoForm {
	@NotEmpty(message = "Precisa preencher o campo")
	private String url;
	//Obs.: o form não tem o campo 'Tema', porque a vinculação do material com o tema será feita separadamente
	
	public MaterialDeEstudoForm(MaterialDeEstudo mat) {
		this.url = mat.getUrl();
	}
}
