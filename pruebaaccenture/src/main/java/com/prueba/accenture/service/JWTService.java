package com.prueba.accenture.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.prueba.accenture.entity.Usuario;

import io.jsonwebtoken.Claims;

public interface JWTService {

	public String create(Authentication auth) throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String getUsername(String token);
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;
	public String resolve(String token);
	String createTokenByUser(Usuario user) throws IOException;
}
