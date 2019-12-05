package com.diego.pedidosspring.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.repositories.CategoriaRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public void instaciateDataBase() {
		Categoria c1 = new Categoria("Informática");
		Categoria c2 = new Categoria("Escritório");
//		Categoria c3 = new Categoria("Computador");
//		Categoria c4 = new Categoria("Esporte");
//		Categoria c5 = new Categoria("Natação");
//		Categoria c6 = new Categoria("Futebol");
//		Categoria c7 = new Categoria("Futsal");
		categoriaRepository.saveAll(Arrays.asList(c1, c2));

	}
}
