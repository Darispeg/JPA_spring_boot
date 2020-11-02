package com.restfull.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.restfull.demo.entity.Usuario;
import com.restfull.demo.repository.GestorUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    @Qualifier("gestorUsuario")
    private GestorUsuario repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
          //SOLUCION para que no de error de encriptacion
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Usuario usuario = repository.findByUsuario(userName);
        return new User(usuario.getUsuario(), encoder.encode(usuario.getContrasena()), 
        usuario.isActivo(), usuario.isActivo(), usuario.isActivo(), usuario.isActivo(), buildgrante(usuario.getRol()));
    }

    public List<GrantedAuthority> buildgrante(byte rol){
        String[] roles = {"LECTOR", "USUARIO", "ADMINISTRADOR"};
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (int i = 0; i < rol; i++) {
            authorities.add(new SimpleGrantedAuthority(roles[i]));
        }

        return authorities;
    }

}
