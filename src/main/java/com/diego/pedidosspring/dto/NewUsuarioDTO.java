package com.diego.pedidosspring.dto;

public class NewUsuarioDTO {

	private String nome;
	private String senha;
	
	public NewUsuarioDTO() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
