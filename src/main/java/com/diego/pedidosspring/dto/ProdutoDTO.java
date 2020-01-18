package com.diego.pedidosspring.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.diego.pedidosspring.model.Produto;

public class ProdutoDTO {

	private Long id;
	@NotBlank(message = "Preenchimento obrigatório")
	@Size(min = 1, max = 4, message = "Deve conter entre 1 e 4 caracteres")
	private String codigo;
	@NotBlank(message = "Preenchimento obrigatório")
	@Length(min=3, max=80, message = "O tamanho deve ter entre 3 e 80")
	private String descricao;
	@Digits(integer = 5, fraction = 2, message="Apenas números com duas casas decimais")
	private Double preco;
	@NotNull(message="Preenchimento obrigatório")
	private Long categoriaId;
	private String categoriaDescricao;
	
	public ProdutoDTO() {}
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.codigo = produto.getCodigo();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.categoriaId = produto.getCategoria().getId();
		this.categoriaDescricao = produto.getCategoria().getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}
	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
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
		ProdutoDTO other = (ProdutoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}