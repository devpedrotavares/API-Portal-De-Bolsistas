package com.compass.portalcompass.config.security;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.compass.portalcompass.entities.Instrutor;
import com.compass.portalcompass.repositories.InstrutorRepositorio;

public class AutenticacaoViaToken extends OncePerRequestFilter {

	private TokenService tokenService;
	private InstrutorRepositorio instrutorRepositorio;

	public AutenticacaoViaToken(TokenService tokenService, InstrutorRepositorio instrutorRepositorio) {
		this.tokenService = tokenService;
		this.instrutorRepositorio = instrutorRepositorio;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValid(token);
		
		if(valido)
			autenticarInstrutor(token);
		
		filterChain.doFilter(request, response);
	}

	private void autenticarInstrutor(String token) {
		Long idInstrutor = tokenService.getIdInstrutor(token);
		Instrutor instrutor = instrutorRepositorio.findById(idInstrutor).get();
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(instrutor, null, instrutor.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
