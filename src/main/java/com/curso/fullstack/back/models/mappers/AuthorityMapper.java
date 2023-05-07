package com.curso.fullstack.back.models.mappers;

import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.curso.fullstack.back.models.entity.Role;

@Component
public class AuthorityMapper implements Function<Role, GrantedAuthority> {

    @Override
    public GrantedAuthority apply(Role role) {
        return new SimpleGrantedAuthority(role.getNombre());
    }

}
