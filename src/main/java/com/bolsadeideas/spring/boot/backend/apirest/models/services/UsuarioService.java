 package com.bolsadeideas.spring.boot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.spring.boot.backend.apirest.models.dao.iUsuarioDAO;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Usuario;
@Service // se registra como un beans
public class UsuarioService implements UserDetailsService,iUsuarioService{
	
	private Logger logger  = LoggerFactory.getLogger(	UsuarioService.class);

	@Autowired
	iUsuarioDAO usuarioDAO; 
	@Transactional(readOnly=true) // el import debe ser de spring
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //cargar usuario por su nombre
		Usuario usuario = usuarioDAO.findByUsername(username); // se llama al metodo del dao y se guarda en usuario
		if(usuario == null) { //validacion para mostrar error en el log
			logger.error("Error , no existe usuario "+ username);
			throw new UsernameNotFoundException("Error , no existe usuario "+ username);
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles() // se crea una lista de los roles que tiene el usuario
				.stream() // stream es un flujo para invocar al map y cambiar el tipo de nuestra lista (Roles es de tipo rol , no GrantesAuthority)
				.map(role-> new SimpleGrantedAuthority(role.getNombre()))// transformacion con el MAP
				.peek(authority -> logger.info("Role: "+ authority.getAuthority())) // peek muestra los elementos actuales del stream , para el log
				.collect(Collectors.toList()); // transforma a lista con el collect

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
		//clase de Spring con el usuario y sus credenciales PARAMETROS:
		//username , password, enables, cuentaNoExpirada , credencialesNoExpiradas, cuentaNoBloqueada, y la lista de autoridades	
	}
	@Override
	@Transactional
	public Usuario findByUsername(String username) {
		
		return usuarioDAO.findByUsername(username);
	}
	

}
