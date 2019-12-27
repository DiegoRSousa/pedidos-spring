package com.diego.pedidosspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.diego.pedidosspring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	Usuario findByNome(String nome);
}
