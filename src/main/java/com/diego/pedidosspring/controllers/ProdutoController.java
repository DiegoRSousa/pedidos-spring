package com.diego.pedidosspring.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diego.pedidosspring.dto.ProdutoDTO;
import com.diego.pedidosspring.model.Produto;
import com.diego.pedidosspring.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<Produto> produtos = produtoService.findAll();
		return ResponseEntity.ok(toDTO(produtos));
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ResponseEntity<ProdutoDTO> find(@PathVariable Long id) {
		ProdutoDTO produtoDTO = new ProdutoDTO(produtoService.findById(id));
		return ResponseEntity.ok(produtoDTO);
	}
	
	@RequestMapping(value="/search/{codigoOuDescricao}", method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> search(@PathVariable String codigoOuDescricao) {
		List<Produto> produtos = produtoService.findByDescricaoContainsIgnoreCaseOrCodigo(codigoOuDescricao, codigoOuDescricao);
		return ResponseEntity.ok(toDTO(produtos));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody ProdutoDTO produtoDTO) {
		Produto produto = produtoService.fromDTO(produtoDTO);
		produtoService.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ProdutoDTO produtoDTO) {
		Produto produto = produtoService.fromDTO(produtoDTO);
		produtoService.update(produto);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private List<ProdutoDTO> toDTO(List<Produto> produtos) {
		return  produtos.stream().map(p -> new ProdutoDTO(p)).collect(Collectors.toList());
	}
}