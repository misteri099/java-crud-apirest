package com.apirest.apirest.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.apirest.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
