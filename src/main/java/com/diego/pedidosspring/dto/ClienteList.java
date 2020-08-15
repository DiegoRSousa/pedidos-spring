package com.diego.pedidosspring.dto;

import java.time.LocalDateTime;

import com.diego.pedidosspring.model.Cliente;

public class ClienteList {

	private Long id;
	private String nome;
	private String cpf;
	private LocalDateTime criadoEm;
	private LocalDateTime atualizadoEm;
	
	public ClienteList(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.criadoEm = cliente.getCriadoEm();
		this.atualizadoEm = cliente.getAtualizadoEm();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	
	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}
}