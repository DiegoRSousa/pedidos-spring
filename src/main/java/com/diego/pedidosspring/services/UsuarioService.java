package com.diego.pedidosspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.dto.NewUsuarioDTO;
import com.diego.pedidosspring.model.Usuario;
import com.diego.pedidosspring.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bpe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario fromDTO(NewUsuarioDTO usuarioDTO) {
		return new Usuario(usuarioDTO.getNome(), bpe.encode(usuarioDTO.getSenha()));		
	}
	
}
