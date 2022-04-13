package com.compass.portalcompass.feignclients.request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.compass.portalcompass.entities.Estagiario;
import com.compass.portalcompass.entities.EstagiarioSprint;
import com.compass.portalcompass.entities.Tema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

	private String ownerRef;
	private String emailFrom;
	private String emailTo;
	private String subject;
	private String text;
	
	public EmailDTO(EstagiarioSprint vinculo) {
		Estagiario estagiario = vinculo.getEstagiario();
		
		this.ownerRef = estagiario.getNome();
		this.emailFrom = "compasstestemailapi@gmail.com";
		this.emailTo = estagiario.getEmail();
		this.subject = "teste";
		this.text = 
				String.format("Bom dia, %s! Segue os resultados da avaliação na %s: \n"
						+ "Nota comportamental: %s \n"
						+ "Nota técnica: %s \n"
						+ "Temas de reforço: %s \n", 
						estagiario.getNome(), 
						vinculo.getSprint().getNome(),
						vinculo.getNotaComportamental().toString(), 
						vinculo.getNotaTecnica().toString(),
						vinculo.getTemasReforco().stream().map(t -> t.getNome()).collect(Collectors.toList()).toString() );
	}
}
