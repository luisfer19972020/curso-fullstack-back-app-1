package com.curso.fullstack.back.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.fullstack.back.models.entity.Cliente;

public interface IClienteService {
    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Page<Cliente> findAllPrueba(Pageable pageable, String busqueda);

    public Cliente save(Cliente cliente);

    public Cliente findById(Long id);

    public void delete(Long id);
}
