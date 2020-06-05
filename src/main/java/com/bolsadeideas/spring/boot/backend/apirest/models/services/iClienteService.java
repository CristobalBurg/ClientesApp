package com.bolsadeideas.spring.boot.backend.apirest.models.services;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Region;

public interface iClienteService { //Aqui van los metodods del crud	
	
	public List<Cliente> findAll();	 // SELECT TODOS

	public Page<Cliente> findAll(Pageable pageable); //SELECT TODOS CON PAGINACION
	
	public Cliente findById(Long id);  // SELECT UNO
	
	public Cliente save(Cliente cliente); // INSERT
	
	public void delete(Long id); //DELETE
	
	public List<Region> findAllRegion();
	
	public Factura findFacturaById(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	public List<Producto> findProductoByNombre(String term);

}
