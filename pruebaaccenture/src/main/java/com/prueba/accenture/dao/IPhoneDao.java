package com.prueba.accenture.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.accenture.entity.Phone;

@Repository
public interface IPhoneDao extends CrudRepository<Phone, Long> {

}
