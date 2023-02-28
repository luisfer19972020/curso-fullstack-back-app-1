package com.curso.fullstack.back.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.fullstack.back.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long> {
    
}
