package com.backend.cliente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static final int ACCESS_TOKEN_VALIDITY_IN_SECONDS = 0;
	private static final int REFRESH_TOKEN_VALIDITY_IN_SECONDS = 0;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override  
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {  
	    security.tokenKeyAccess("permitAll()")  
	            .checkTokenAccess("isAuthenticated()")  
	            .allowFormAuthenticationForClients();
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();

        config.addAllowedHeader("access-control-allow-origin");
        config.addAllowedHeader("Access-Control-Allow-Headers");
        config.addAllowedHeader("Access-Control-Allow-Credentials");
        config.addAllowedHeader("Access-Control-Allow-Methods");
        
        source.registerCorsConfiguration("/oauth/token", config);
        CorsFilter filter = new CorsFilter(source);
        security.addTokenEndpointAuthenticationFilter(filter);
	}
	
	@Override  
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {  
	    clients.inMemory()  
	            .withClient("client-id")  
	            .secret(new BCryptPasswordEncoder().encode("secret-id"))
	            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")  
	            .scopes("read", "write", "trust")  
	            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_IN_SECONDS)  
	            .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_IN_SECONDS);
	}
	
	@Override  
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {  
	    endpoints.authenticationManager(authenticationManager)  
	                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}
}
