package com.compass.portalcompass.exception;

public class NaoEncontradoExcecao extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NaoEncontradoExcecao(Object id) {
		super("Id não encontrado = " + id);
	}

}
