package com.diego.pedidosspring.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.diego.pedidosspring.model.Cliente;

public class ClienteForm {

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=480, message = "O tamanho deve ter entre 3 e 480")
	private String nome;
	private String cpf;
	private String logradouro;
	private String numero;
	private String bairro;
	
	public ClienteForm(String nome, String cpf, String logradouro, String numero, String bairro) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
	}
	
	public Cliente toModel() {
		return new Cliente(nome, cpf, logradouro, numero, bairro);
	}
	
}
