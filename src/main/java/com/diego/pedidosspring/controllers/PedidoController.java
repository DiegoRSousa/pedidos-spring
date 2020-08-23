package com.diego.pedidosspring.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.pedidosspring.dto.PedidoList;
import com.diego.pedidosspring.exceptions.ObjectNotFoundException;
import com.diego.pedidosspring.model.Pedido;
import com.diego.pedidosspring.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<PedidoList>> getAll() {
		return ResponseEntity.ok(pedidoRepository.findAll().stream().map(PedidoList :: new).collect(Collectors.toList()));
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<PedidoList>> findAllPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		var pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		var pedidos = pedidoRepository.findAll(pageRequest);
		Page<PedidoList> pedidosList = pedidos.map(PedidoList :: new);

		return ResponseEntity.ok(pedidosList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoList> find(@PathVariable Long id) {
		var pedido = findById(id);
		return ResponseEntity.ok(new PedidoList(pedido));
	}
	
	private Pedido findById(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Pedido.class.getName()));
	}
}