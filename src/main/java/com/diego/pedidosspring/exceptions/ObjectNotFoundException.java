package com.diego.pedidosspring.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(Long id, String clazz) {
		super("Objeto não encontrado!" + " Id: " + id + ", Tipo: " + clazz);
	}
	
	public ObjectNotFoundException(String descricao) {
		super("Nenhum objeto encontrado com a descrição: " + descricao);
	}
}
