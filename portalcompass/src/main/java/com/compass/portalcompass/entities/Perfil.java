package com.compass.portalcompass.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Data
public class Perfil implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String nome;
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

}
