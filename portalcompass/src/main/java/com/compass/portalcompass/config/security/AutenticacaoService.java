package com.compass.portalcompass.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compass.portalcompass.entities.Instrutor;
import com.compass.portalcompass.repositories.InstrutorRepositorio;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private InstrutorRepositorio instrutorRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Instrutor> instrutor = instrutorRepositorio.findByEmail(username);
		if(instrutor.isPresent()) {
			instrutor.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
