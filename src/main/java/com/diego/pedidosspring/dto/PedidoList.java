package com.diego.pedidosspring.dto;

import java.time.LocalDateTime;

import com.diego.pedidosspring.model.Pedido;

public class PedidoList {

	private Long id;
	private String numero;
	private String cliente;
	private Double subTotal;
	private Double acrescimo;
	private Double desconto;
	private Double total;
	private LocalDateTime data;
	
	public PedidoList(Pedido pedido) {
		this.id = pedido.getId();
		this.numero = pedido.getNumero();
		this.cliente = pedido.getCliente().getNome();
		this.subTotal = pedido.getSubTotal();
		this.acrescimo = pedido.getAcrescimo();
		this.desconto = pedido.getDesconto();
		this.total = pedido.getTotal();
		this.data = pedido.getData();
	}
	
	public Long getId() {
		return id;
	}
	public String getNumero() {
		return numero;
	}
	public String getCliente() {
		return cliente;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public Double getAcrescimo() {
		return acrescimo;
	}
	public Double getDesconto() {
		return desconto;
	}
	public Double getTotal() {
		return total;
	}
	public LocalDateTime getData() {
		return data;
	}
}