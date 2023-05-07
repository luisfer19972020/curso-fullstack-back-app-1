package com.curso.fullstack.back.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.fullstack.back.models.dao.IUsuarioDao;
import com.curso.fullstack.back.models.entity.Usuario;
import com.curso.fullstack.back.models.mappers.AuthorityMapper;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private IUsuarioDao usuarioDao;
    @Autowired
    private AuthorityMapper mapper;

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            logger.error("No existe el usuario en el sistema {}", username);
            throw new UsernameNotFoundException("No existe el usuario en el sistema");
        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(mapper)
                .peek(role -> logger.info("Role: ".concat(role.getAuthority())))
                .collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
                authorities);

    }

}
