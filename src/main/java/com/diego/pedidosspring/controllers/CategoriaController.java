package com.diego.pedidosspring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.pedidosspring.exceptions.DataIntegrityException;
import com.diego.pedidosspring.exceptions.ObjectNotFoundException;
import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.repositories.CategoriaRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@ApiOperation(value="Busca todas categorias")
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Categoria>> findAllPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		var pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return ResponseEntity.ok(categoriaRepository.findAll(pageRequest));
	}
	
	@ApiOperation(value="Busca por id")
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		return ResponseEntity.ok(findById(id));
	}
	
	@ApiOperation(value="Busca por descrição")
	@GetMapping("/search/{descricao}")
	public ResponseEntity<List<Categoria>> search(@PathVariable String descricao) {
		return ResponseEntity.ok(categoriaRepository.findByDescricaoContainingIgnoreCase(descricao));
	}
	
	@ApiOperation(value="Insere categoria")
	@PostMapping
	public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaRepository.save(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Atualiza categoria")
	@PutMapping
	public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria);
	}
	
	@ApiOperation(value="Remove categoria")
	@ApiResponses(value = {@ApiResponse(code = 400, 
			message = "Não é possível excluir uma categoria que possui produtos")})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		var categoria = findById(id);
		try {
			categoriaRepository.delete(categoria);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posível excluir uma categoria que possui produtos!");
		}
		
		return ResponseEntity.noContent().build();
	}
	
	private Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Categoria.class.getName()));
	}
}