package com.backend.cliente.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.cliente.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByCnpjCpf(String cnpjCpf);
	
}
