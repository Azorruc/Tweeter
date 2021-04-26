package com.tweeter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweeter.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
