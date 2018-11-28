package com.prueba.accenture.service;

import com.prueba.accenture.entity.Usuario;

public interface UserService {
	
	public Usuario getUsuario(Long id);
	public boolean verificarToken(Usuario usuario, String token);
	public boolean verificarLastLogin(Usuario usuario);

}
