package com.bolsadeideas.spring.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Producto;

public interface iProductosDAO extends CrudRepository<Producto, Long> {
	@Query("select p from Producto p where p.nombre like %?1%") // query personalizada que usa el LIKE para buscar valores
	public List<Producto> findByName(String term);

	public List<Producto> findByNombreContainingIgnoreCase(String term); //query JPA que hace la misma wea palabras claves : containing y ignorecase		
}
