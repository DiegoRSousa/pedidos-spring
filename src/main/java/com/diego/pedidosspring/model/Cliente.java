package com.diego.pedidosspring.model;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=480, message = "O tamanho deve ter entre 3 e 480")
	private String nome;
	private String cpf;
	@Embedded
	private Endereco endereco;
	private LocalDateTime criadoEm = LocalDateTime.now();
	private LocalDateTime atualizadoEm;
	
	public Cliente () {}
	
	public Cliente (String nome) {
		this.nome = nome;
	}
	
	public Cliente (String nome, String cpf, String logradouro, String numero, String bairro) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = new Endereco(logradouro, numero, bairro);
	}
	
	public void update(Cliente newCliente) {
		this.nome = newCliente.nome;
		this.cpf = newCliente.cpf;
		this.endereco = new Endereco(
								newCliente.endereco.getLogradouro(), 
								newCliente.endereco.getNumero(), 
								newCliente.endereco.getBairro());
		this.atualizadoEm = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(LocalDateTime atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
