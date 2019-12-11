package com.diego.pedidosspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.model.Categoria;
import com.diego.pedidosspring.repositories.CategoriaRepository;
import com.diego.pedidosspring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!" + " Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findByDescricaoContainingIgnoreCase(String descricao) {
		List<Categoria> categorias = categoriaRepository.findByDescricaoContainingIgnoreCase(descricao);
		if(categorias.isEmpty())
			throw new ObjectNotFoundException("Nenhum objeto encontrado com a descrição: " + descricao);
		return categorias;
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void delete(Long id) {
		findById(id);
		categoriaRepository.deleteById(id);
	}
}