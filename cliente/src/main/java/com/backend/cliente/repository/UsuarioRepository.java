package com.backend.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.cliente.models.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);
}
