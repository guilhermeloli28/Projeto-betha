package com.backend.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.cliente.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
}
