package com.diego.pedidosspring.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.services.CategoriaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value="Busca todas categorias")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> findAll() {
		return ResponseEntity.ok(categoriaService.findAll());
	}
	
	@ApiOperation(value="Busca por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		return ResponseEntity.ok(categoriaService.findById(id));
	}
	
	@ApiOperation(value="Busca por descrição")
	@RequestMapping(value = "/search/{descricao}", method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> search(@PathVariable String descricao) {
		return ResponseEntity.ok(categoriaService.findByDescricaoContainingIgnoreCase(descricao));
	}
	
	@ApiOperation(value="Insere categoria")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Categoria categoria) {
		categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Atualiza categoria")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria) {
		categoriaService.update(categoria);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Remove categoria")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos")})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}