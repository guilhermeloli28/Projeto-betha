package com.backend.cliente.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cliente.models.Cliente;
import com.backend.cliente.repository.ClienteRepository;
import com.backend.cliente.service.ClienteService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@PostMapping("/cliente")
	public ResponseEntity insereNovoCliente(@RequestBody Cliente cliente) {
		
		Optional<Cliente> obj = clienteRepository.findByCnpjCpf(cliente.getCnpjCpf()); 
		
		if(obj.isPresent()) {
			throw new RuntimeException("CPF OU CNPJ já existe.");
		}
		
		clienteService.save(cliente);
		
		return ResponseEntity.ok().build();
	}
}
