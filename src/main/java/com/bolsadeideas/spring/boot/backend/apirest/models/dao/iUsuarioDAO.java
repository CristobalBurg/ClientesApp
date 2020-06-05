package com.bolsadeideas.spring.boot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository; 
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Usuario;
public interface iUsuarioDAO extends CrudRepository<Usuario, Long> {
	//convencion de spring findBy+campo de la tabla para realizar consultas personalizadas aplicables al crud
	public Usuario findByUsername(String username);
	// otra forma con la notacion query , donde puede ser cualquier nombre del metodo
	//hay que escribir en la notacion la sentencia SQL
	@Query("Select u from Usuario u where u.username =?1")
	public Usuario findByUsername2(String username);
	

}
