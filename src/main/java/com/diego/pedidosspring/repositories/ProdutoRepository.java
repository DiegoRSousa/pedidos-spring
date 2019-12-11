package com.diego.pedidosspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.diego.pedidosspring.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Transactional(readOnly = true)
	List<Produto> findByDescricaoContainsIgnoreCaseOrCodigo(String descricao, String codigo);	
}
