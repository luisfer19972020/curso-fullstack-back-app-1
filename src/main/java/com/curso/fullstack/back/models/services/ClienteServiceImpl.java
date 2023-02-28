package com.curso.fullstack.back.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.fullstack.back.models.dao.IClienteDao;
import com.curso.fullstack.back.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return ((List<Cliente>) clienteDao.findAll());
    }

}
