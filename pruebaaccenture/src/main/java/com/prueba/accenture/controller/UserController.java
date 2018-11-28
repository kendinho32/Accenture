package com.prueba.accenture.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.accenture.entity.Usuario;
import com.prueba.accenture.payload.ApiResponse;
import com.prueba.accenture.service.JWTServiceImpl;
import com.prueba.accenture.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/perfil/{id}")
    public ResponseEntity<?> perfilUser(@PathVariable(value = "id") Long id, HttpServletRequest servletRequest) {
		// verifico que exista el usuario dentro de la BD
		Usuario user = userService.getUsuario(id);
		
		if(user == null) {
			return new ResponseEntity(new ApiResponse(false, "El usuario no se encuentra registrado!"), HttpStatus.UNAUTHORIZED);
		}
		String[] header = servletRequest.getHeader(JWTServiceImpl.HEADER_STRING).split(" ");	
		
		// Verifico que el token corresponda al usuario
		if(!userService.verificarToken(user, header[1])) {
			return new ResponseEntity(new ApiResponse(false, "El token no corresponde al usuario!"), HttpStatus.UNAUTHORIZED);
		}
		
		// verifico el tiempo desde el ultimo login
		if(userService.verificarLastLogin(user)) {
			return new ResponseEntity(new ApiResponse(false, "Sesión inválida!"), HttpStatus.BAD_GATEWAY);
		}
        
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
