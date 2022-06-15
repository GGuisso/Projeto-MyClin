package com.myclin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String Username);
}
