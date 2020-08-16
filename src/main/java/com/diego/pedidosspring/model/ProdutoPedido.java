package com.diego.pedidosspring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

@Entity
public class ProdutoPedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Digits(integer = 7, fraction = 2, message="Apenas números com duas casas decimais")
	private Double quantidade;
	@Digits(integer = 7, fraction = 2, message="Apenas números com duas casas decimais")
	private Double preco;
	@Digits(integer = 7, fraction = 2, message="Apenas números com duas casas decimais")
	private Double subTotal;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Pedido pedido;
}