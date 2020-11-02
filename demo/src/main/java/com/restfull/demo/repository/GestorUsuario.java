package com.restfull.demo.repository;

import java.io.Serializable;

import com.restfull.demo.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("gestorUsuario")
public interface GestorUsuario extends JpaRepository<Usuario, Serializable> {
    
    public abstract Usuario findByUsuario(String usuario);

}
