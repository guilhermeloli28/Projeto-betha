package com.backend.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.cliente.models.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
	
}
