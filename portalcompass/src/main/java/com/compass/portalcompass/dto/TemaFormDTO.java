package com.compass.portalcompass.dto;

import javax.validation.constraints.NotBlank;

import com.compass.portalcompass.entities.Tema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaFormDTO {

	@NotBlank(message = "Precisa preencher o nome")
	private String nome;
	
	public TemaFormDTO(Tema tema) {
		this.nome = tema.getNome();
	}
}