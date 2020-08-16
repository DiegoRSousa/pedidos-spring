package com.diego.pedidosspring.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	private LocalDateTime data;
	@ManyToOne
	private Cliente cliente;
	@OneToMany
	private List<ProdutoPedido> produtosPedido;
	private Double subTotal;
	private LocalDateTime criadoEm = LocalDateTime.now();
	
	public Long getId() {
		return id;
	}
	public String getNumero() {
		return numero;
	}
	public LocalDateTime getData() {
		return data;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public List<ProdutoPedido> getProdutosPedido() {
		return produtosPedido;
	}
	public Double getSubTotal() {
		return subTotal;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
