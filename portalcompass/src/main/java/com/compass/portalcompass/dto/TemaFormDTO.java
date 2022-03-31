package com.compass.portalcompass.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.compass.portalcompass.entities.MaterialDeEstudo;
import com.compass.portalcompass.entities.Sprint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaFormDTO {
	
	@NotEmpty(message = "Precisa preencher o nome")
	private String nome;
}
