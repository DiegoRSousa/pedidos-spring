package com.diego.pedidosspring.dto;

public class CredenciaisDTO {

	private String nome;
	private String senha;
	
	public CredenciaisDTO() { }
	
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