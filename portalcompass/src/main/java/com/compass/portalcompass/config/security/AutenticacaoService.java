package com.compass.portalcompass.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.entities.Usuario;
import com.compass.portalcompass.repositories.UsuarioRepositorio;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepositorio.findByEmail(username);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}