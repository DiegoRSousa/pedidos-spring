package com.diego.pedidosspring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Preenchimento obrigatório")
	@Size(min = 1, max = 4, message = "Deve conter entre 1 e 4 caracteres")
	@Column(unique = true)
	private String codigo;
	@NotBlank(message = "Preenchimento obrigatório")
	@Length(min=3, max=80, message = "O tamanho deve ter entre 3 e 80")
	private String descricao;
	@Digits(integer = 5, fraction = 2, message="Apenas números com duas casas decimais")
	private Double preco;

	@ManyToOne
	private Categoria Categoria;
	
	public Produto() {}
	public Produto(String codigo, String descricao, Double preco, Categoria categoria) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		Categoria = categoria;
	}
	public Produto(Long id, String codigo, String descricao, Double preco, Categoria categoria) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		Categoria = categoria;
	}
	public String getCodigo() {
		return codigo;
	}
	public void SetCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Categoria getCategoria() {
		return Categoria;
	}
	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
