package com.diego.pedidosspring.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.enums.Perfil;
import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.model.Cliente;
import com.diego.pedidosspring.model.Produto;
import com.diego.pedidosspring.model.Usuario;
import com.diego.pedidosspring.repositories.CategoriaRepository;
import com.diego.pedidosspring.repositories.ClienteRepository;
import com.diego.pedidosspring.repositories.ProdutoRepository;
import com.diego.pedidosspring.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder bpe;

	public void instaciateDataBase() {
		Usuario u1 = new Usuario("admin", bpe.encode("123"));
		u1.AddPerfil(Perfil.ADMIN);
		Usuario u2 = new Usuario("padrao", bpe.encode("123"));
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
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
		
		
		var cl1 = new Cliente("Cliente 1");
		var cl2 = new Cliente("Cliente 2");
		var cl3 = new Cliente("Cliente 3");
		var cl4 = new Cliente("Cliente 4");
		var cl5 = new Cliente("Cliente 5");
		var cl6 = new Cliente("Cliente 6");
		var cl7 = new Cliente("Cliente 7");
		var cl8 = new Cliente("Cliente 8");
		var cl9 = new Cliente("Cliente 9");
		var cl10 = new Cliente("Cliente 10");
		clienteRepository.saveAll(Arrays.asList(cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9, cl10));
		
	}
}
