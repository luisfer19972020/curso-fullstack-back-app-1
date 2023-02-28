package com.curso.fullstack.back.models.services;

import java.util.List;

import com.curso.fullstack.back.models.entity.Cliente;

public interface IClienteService {
    public List<Cliente> findAll();
}
