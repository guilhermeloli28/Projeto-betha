package com.backend.cliente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.backend.cliente.models.Usuario;
import com.backend.cliente.models.dto.UsuarioCustomDTO;
import com.backend.cliente.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UsuarioRepository usuarioRepository) throws Exception {
	    if (usuarioRepository.count() == 0) {
	        Usuario usuario = new Usuario();
	        usuario.setLogin("admin");
	        usuario.setSenha(new BCryptPasswordEncoder().encode("admin"));
	        usuarioRepository.save(usuario);
	    }
	    
		builder.userDetailsService(login -> new UsuarioCustomDTO(usuarioRepository.findByLogin(login)))
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }
	
	@Bean  
	public static BCryptPasswordEncoder passwordEncoder() {  
	    return new BCryptPasswordEncoder();  
	}
}
