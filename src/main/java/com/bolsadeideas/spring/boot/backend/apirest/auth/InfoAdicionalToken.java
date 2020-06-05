package com.bolsadeideas.spring.boot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Usuario;
import com.bolsadeideas.spring.boot.backend.apirest.models.services.iUsuarioService;

//clase para poder ingresar informacion adicional en el cuerpo del token
@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	iUsuarioService usuarioService;

	
	@Override
	//metodo que agrega info adicional al token , recibe el token y la autenticacion
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String,Object> info = new HashMap<>(); // la info se guarda en un map
		Usuario usuario = usuarioService.findByUsername(authentication.getName()); // busca el usuario
		
		info.put("nombre", usuario.getNombre()); //info adicional sacada de la clase entity
		info.put("apellido", usuario.getApellido());
		info.put("email",usuario.getEmail());
		 ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info); // setea la info adicional
		return accessToken;
	}

}
