package com.curso.fullstack.back.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.fullstack.back.models.dao.IClienteDao;
import com.curso.fullstack.back.models.dao.IRegionDao;
import com.curso.fullstack.back.models.entity.Cliente;
import com.curso.fullstack.back.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private IClienteDao clienteDao;

    @Autowired
    private IRegionDao regionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return ((List<Cliente>) clienteDao.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return this.clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return this.clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.clienteDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAllPrueba(Pageable pageable, String busqueda) {
        return this.clienteDao.findAllPrueba(busqueda, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> getAllRegions() {
        return regionDao.findAll();
    }

}
