package com.diego.pedidosspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diego.pedidosspring.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Transactional(readOnly = true)
	List<Categoria> findByDescricaoContainingIgnoreCase(String descricao);
}
