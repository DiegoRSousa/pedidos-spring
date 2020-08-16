package com.diego.pedidosspring.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Endereco {

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=480, message = "O tamanho deve ter entre 3 e 480")
	private String logradouro;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=1, max=10, message = "O tamanho deve ter entre 1 e 10")
	private String numero;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=480, message = "O tamanho deve ter entre 3 e 480")
	private String bairro;
	
	public Endereco() { }
	public Endereco(String logradouro, String numero, String bairro) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
