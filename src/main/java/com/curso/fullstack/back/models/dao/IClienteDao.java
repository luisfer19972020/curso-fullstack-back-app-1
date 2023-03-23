package com.curso.fullstack.back.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.fullstack.back.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente,Long> {
    
}
