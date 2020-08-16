package com.diego.pedidosspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.pedidosspring.model.Pedido;
import com.diego.pedidosspring.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		return ResponseEntity.ok(pedidoRepository.findAll());
	}
	
}
