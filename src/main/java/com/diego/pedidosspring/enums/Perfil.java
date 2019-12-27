package com.diego.pedidosspring.enums;

public enum Perfil {
	ADMIN("ROLE_ADMIN"),
	PADRAO("ROLE_PADRAO");
	
	private String descricao;
	
	private Perfil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	
}
