package com.backend.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cliente.models.Usuario;
import com.backend.cliente.repository.UsuarioRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;
	
	@PostMapping("/usuario")
	public Usuario insereNovoUsuario(@RequestBody Usuario usuario) {
		usuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	@GetMapping("/usuario")
	public List<Usuario> getUsers(){
		return usuarioRepository.findAll();
	}
}
