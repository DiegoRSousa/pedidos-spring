package com.diego.pedidosspring.dto;

import com.diego.pedidosspring.model.Produto;

public class ProdutoDTO {

	private Long id;
	private String codigo;
	private String descricao;
	private Double preco;
	private Long categoriaId;
	
	
	public ProdutoDTO() {}
	public ProdutoDTO(String codigo, String descricao, Double preco, Long categoriaId) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.categoriaId = categoriaId;
	}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.codigo = produto.getCodigo();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.categoriaId = produto.getCategoria().getId();
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