package com.diego.pedidosspring.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.model.Produto;
import com.diego.pedidosspring.repositories.CategoriaRepository;
import com.diego.pedidosspring.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public void instaciateDataBase() {
		Categoria c1 = new Categoria("Informática");
		Categoria c2 = new Categoria("Escritório");
		Categoria c3 = new Categoria("Esporte");
		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Produto p1 = new Produto("0001", "Computador I3", 1500.00, c1);
		Produto p2 = new Produto("0002", "NoteBook", 1800.00, c1);
		Produto p3 = new Produto("0003", "Mesa escritório", 300.00, c2);
		Produto p4 = new Produto("0004", "Bike Soul 29", 1700.00, c3);
		Produto p5 = new Produto("0005", "Bike Caloi 29", 1650.00, c3);
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	}
}
