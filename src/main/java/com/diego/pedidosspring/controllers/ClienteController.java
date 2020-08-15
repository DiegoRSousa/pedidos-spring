package com.diego.pedidosspring.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.pedidosspring.dto.ClienteForm;
import com.diego.pedidosspring.dto.ClienteList;
import com.diego.pedidosspring.exceptions.ObjectNotFoundException;
import com.diego.pedidosspring.model.Cliente;
import com.diego.pedidosspring.repositories.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<ClienteList>> findAll() {
		return ResponseEntity.ok(clienteRepository.findAll().stream().map(ClienteList :: new).collect(Collectors.toList()));
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteList>> findAllPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		var pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		var clientes = clienteRepository.findAll(pageRequest);
		Page<ClienteList> clientesList = clientes.map(ClienteList :: new);

		return ResponseEntity.ok(clientesList);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteList> find(@PathVariable Long id) {
		var cliente = findById(id);
		return ResponseEntity.ok(new ClienteList(cliente));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteForm clienteForm) {
		var cliente = clienteRepository.save(clienteForm.toModel());
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody ClienteForm clienteForm) {
		var cliente = findById(id);
		cliente.update(clienteForm.toModel());
		clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		var cliente = findById(id);
		clienteRepository.delete(cliente);
		return ResponseEntity.noContent().build();
	}
	
	private Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Cliente.class.getName()));
	}
}