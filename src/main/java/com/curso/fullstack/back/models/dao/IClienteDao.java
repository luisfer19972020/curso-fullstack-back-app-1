package com.curso.fullstack.back.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.fullstack.back.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
    @Query(value = "select * from clientes c where c.nombre like %:busqueda% or c.apellido like %:busqueda%", countQuery = "select count(*) from clientes c where c.nombre like %:busqueda% or c.apellido like %:busqueda%", nativeQuery = true)
    public Page<Cliente> findAllPrueba(@Param("busqueda") String busqueda, Pageable pageable);
}
