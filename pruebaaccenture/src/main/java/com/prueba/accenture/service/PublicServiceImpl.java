package com.prueba.accenture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba.accenture.dao.IPhoneDao;
import com.prueba.accenture.dao.IUserDao;
import com.prueba.accenture.entity.Phone;
import com.prueba.accenture.entity.Usuario;
import com.prueba.accenture.payload.SignUpRequest;

@Service
public class PublicServiceImpl implements PublicService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IPhoneDao phoneRepository;
	
	@Autowired
	IUserDao userRepository;
	
	@Autowired
	private JWTService jwtService;

	@Override
	public Usuario generateAndSaveUser(SignUpRequest request) throws IOException {
		// Creating user's account
		Usuario user = new Usuario();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
				
		List<Phone> listPhones = new ArrayList<>();
		setPhones(request, listPhones);
				
		user.setToken(jwtService.createTokenByUser(user));
		user.setPhones(listPhones);
		user.setModified(new Date());
		user.setLastLogin(new Date());
		return userRepository.save(user);
	}
	
	/**
	 * Metodo que se encarga de setear la lista de telefonos con los que se va almacenar
	 * el usuario dentro de la BD
	 * 
	 * @param <SignUpRequest> Objeto que contiene las caracteristicas recibida para 
	 * 			registrar al usuario
	 * @param <listPhones> Lista de telefonos que va almacenar la informacion recibida para el usuario
	 **/
	private void setPhones(SignUpRequest request, List<Phone> listPhones) {
		for(com.prueba.accenture.payload.Phone elemntPhone : request.getPhones()) {
			Phone phone = new Phone();
			phone.setNumber(elemntPhone.getNumber());
			phone.setCitycode(elemntPhone.getCitycode());
			phone.setContrycode(elemntPhone.getContrycode());
			
			listPhones.add(phoneRepository.save(phone));
		}
	}

	@Override
	public Usuario updateUser(Usuario usuario) throws IOException {
		return userRepository.save(usuario);
	}

}
