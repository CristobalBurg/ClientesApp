package com.bolsadeideas.spring.boot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
@Configuration
@EnableAuthorizationServer
public class AutorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	@Autowired
	InfoAdicionalToken infoAdicionalToken;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")// permiso pa que cualquiera se pueda autenticar
		.checkTokenAccess("isAuthenticated()");  //solo pueden acceder usuarios autenticados
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { //registrar todos los clientes , en este caso angularapp
		
		//CREDENCIALES DE LA APLICACION ANGULAR
		clients.inMemory().withClient("angularapp") //se registra el nombre el cliente
		.secret(passwordEncoder.encode("12345")) //se usa el passwordEncoder para encriptar la contraseña
		.scopes("read","write") //los accesos del cliente , en este caso totales
		.authorizedGrantTypes("password","refresh_token")//password te dice que se hara por password y refresh token que se reinicia cada vez que se inicia
		.accessTokenValiditySeconds(3600) //duracion del token de 1 hr
		.refreshTokenValiditySeconds(3600); //duracion del roken reiniciado de 1 hr
	} 

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain(); //Mejoras al toke para añadir info
		tokenEnhancerChain.setTokenEnhancers(	Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager)
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
		
	}
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();	
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA); //rsa es una forma de firma criptografica , la rsa privada FIRMA el jwt
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA); //rsa publica valida que el token no haya sido modificado
		return jwtAccessTokenConverter;
	}
	
	

}
