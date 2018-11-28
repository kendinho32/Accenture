package com.prueba.accenture.service;

import java.io.IOException;

import com.prueba.accenture.entity.Usuario;
import com.prueba.accenture.payload.SignUpRequest;

public interface PublicService {
	
	public Usuario generateAndSaveUser(SignUpRequest request)  throws IOException;
	public Usuario updateUser(Usuario usuario)  throws IOException;

}
