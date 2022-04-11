package com.compass.portalcompass.dto;

import com.compass.portalcompass.entities.MaterialDeEstudo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDeEstudoDTO {
	private Long id;
	
	private String nome;
	private String url;
	private TemaDTO tema;

	public MaterialDeEstudoDTO(MaterialDeEstudo mat) {
		this.id = mat.getId();
		this.nome = mat.getNome();
		this.url = mat.getUrl();
		this.tema = new TemaDTO(mat.getTema());
	}

}
