package com.bolsadeideas.spring.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Region;
/*Interfaz del crud de la tabla cliente, extiende de la interfaz Crud Repository de JPA
 esta interfaz es magia negra que implementa el crud automaticamente ,  poniendole como 
 argumentos : EL NOMBRE DE LA CLASE ENTITY (nombre de la tabla) y el TIPO DE DATO DEL PK*/
public interface iClienteDAO extends JpaRepository<Cliente, Long> { 
	/*Para usar la paginacion debemos heredar de JPA repository , un repositorio mas especifico
	 * que el crud , que contiende metodos de tipo PAGE que son los que permiten mostrar
	 * los datos en diferentes paginas*/
	@Query("from Region")
	public List<Region> findAllRegion();

}
