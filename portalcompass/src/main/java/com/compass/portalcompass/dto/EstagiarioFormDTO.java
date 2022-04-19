package com.compass.portalcompass.dto;





import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.enums.TipoBolsa;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstagiarioFormDTO {
	
	@NotNull(message = "Precisa preencher o campo")
	private Long matricula;
	@NotBlank(message = "Precisa preencher o campo")
	private String nome;
	@NotBlank(message = "Precisa preencher um email")
	private String email;
	@NotNull(message = "Precisa preencher com o tipo da bolsa")
	private TipoBolsa tipoBolsa;
	
	public EstagiarioFormDTO(Estagiario estagiario) {
		this.matricula = estagiario.getMatricula();
		this.nome = estagiario.getNome();
		this.email = estagiario.getEmail();
		this.tipoBolsa = estagiario.getTipoBolsa();
	}
}
