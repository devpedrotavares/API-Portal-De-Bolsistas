package com.compass.portalcompass.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaFormDTO {

	private Long id;
	@NotEmpty(message = "Precisa preencher o nome")
	private String nome;
}
