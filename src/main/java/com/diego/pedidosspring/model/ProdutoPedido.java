package com.diego.pedidosspring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

@Entity
public class ProdutoPedido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Digits(integer = 5, fraction = 2, message="Apenas números com duas casas decimais")
	private String quantidade;
	@Digits(integer = 5, fraction = 2, message="Apenas números com duas casas decimais")
	private Double preco;
	@ManyToOne
	private Produto produto;
}