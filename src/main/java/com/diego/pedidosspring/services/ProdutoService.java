package com.diego.pedidosspring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.dto.ProdutoDTO;
import com.diego.pedidosspring.exceptions.ConstraintException;
import com.diego.pedidosspring.exceptions.ObjectNotFoundException;
import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.model.Produto;
import com.diego.pedidosspring.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaService categoriaService;
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(id, Produto.class.getName()));
	}
	
	public List<Produto> findByDescricaoContainsIgnoreCaseOrCodigo(String descricao, String codigo) {
		List<Produto> produtos = produtoRepository.findByDescricaoContainsIgnoreCaseOrCodigo(descricao, codigo);
		if(produtos.isEmpty())
			throw new ObjectNotFoundException(descricao);
		return produtos;
	}
	
	public Produto save(Produto produto) {
		if(produtoRepository.findByCodigo(produto.getCodigo()) != null)
			throw new ConstraintException("Código:"+ produto.getCodigo() +" já existe!");
		return produtoRepository.save(produto);
	}
	
	public Produto update(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void delete(Long id) {
		findById(id);
		produtoRepository.deleteById(id);
	}
	
	public Produto fromDTO(ProdutoDTO produtoDTO) {
		Categoria categoria = categoriaService.findById(produtoDTO.getCategoriaId());
		return new Produto(produtoDTO.getId(), produtoDTO.getCodigo(), produtoDTO.getDescricao(), 
				produtoDTO.getPreco(), categoria);
	}
	
	public List<ProdutoDTO> toDTO(List<Produto> produtos) {
		return produtos.stream().map(p -> new ProdutoDTO(p)).collect(Collectors.toList());
	}
}