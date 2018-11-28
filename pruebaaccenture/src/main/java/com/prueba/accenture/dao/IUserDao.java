package com.prueba.accenture.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.accenture.entity.Usuario;

@Repository
public interface IUserDao extends CrudRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
	
	public Usuario findByEmailAndPassword(String email, String password);
	
	public Optional<Usuario> findById(Long id);
}
