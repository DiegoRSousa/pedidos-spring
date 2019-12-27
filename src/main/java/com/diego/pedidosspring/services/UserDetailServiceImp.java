package com.diego.pedidosspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diego.pedidosspring.model.Usuario;
import com.diego.pedidosspring.repositories.UsuarioRepository;
import com.diego.pedidosspring.security.UsuarioSS;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNome(nome);
		if(usuario == null)
			throw new UsernameNotFoundException(nome);
		return new UsuarioSS(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getPerfis());
	}
}
