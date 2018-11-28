package com.prueba.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.accenture.dao.IUserDao;
import com.prueba.accenture.entity.Usuario;
import com.prueba.accenture.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	IUserDao userRepository;

	public Usuario getUsuario(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@Override
	public boolean verificarToken(Usuario usuario, String token) {
		return usuario.getToken().equalsIgnoreCase(token);
	}

	@Override
	public boolean verificarLastLogin(Usuario usuario) {
		boolean isMayor30 = false;
		int diferencia=(int) ((usuario.getLastLogin().getTime()-usuario.getModified().getTime())/1000);
		int minutos=0;
	
        minutos=(int)Math.floor(diferencia/60);
        diferencia=diferencia-(minutos*60);
        
        if(minutos > 30) {
        	isMayor30 =  true;
        }
		return isMayor30;
	}

}
