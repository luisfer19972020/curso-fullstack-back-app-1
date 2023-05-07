package com.curso.fullstack.back.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.fullstack.back.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    /* public Usuario findByUsernameAndEmail(String usarname, String email);

    @Query("select u from Usuario u where u.username=?1")
    public Usuario findByusername(String username); */

    public Usuario findByUsername(String usarname);
}
