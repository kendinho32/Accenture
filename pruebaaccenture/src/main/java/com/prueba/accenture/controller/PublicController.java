package com.prueba.accenture.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.accenture.entity.Usuario;
import com.prueba.accenture.payload.ApiResponse;
import com.prueba.accenture.payload.LoginRequest;
import com.prueba.accenture.payload.SignUpRequest;
import com.prueba.accenture.service.JpaUserDetailsService;
import com.prueba.accenture.service.PublicService;

@RestController
@RequestMapping("/api/auth")
public class PublicController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JpaUserDetailsService userService;
	
	@Autowired
	private PublicService publicService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest request) throws IOException {
		
		if (userService.verificarEmail(request.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "El correo ya se encuentra registrado!"), HttpStatus.BAD_REQUEST);
		}
		
		Usuario user = publicService.generateAndSaveUser(request);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) throws IOException {
		Usuario user = userService.verificarUser(request.getEmail(), request.getPassword());

		if (user == null) {
			return new ResponseEntity(new ApiResponse(false, "Usuario o contraseña inválidos!"), HttpStatus.UNAUTHORIZED);
		}
		
		user.setLastLogin(new Date());
		user = publicService.updateUser(user);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
