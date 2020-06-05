package com.bolsadeideas.spring.boot.backend.apirest.models.services;

import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Usuario;

public interface iUsuarioService {
	public Usuario findByUsername(String username);	

}
