package com.bolsadeideas.spring.boot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.spring.boot.backend.apirest.models.dao.iClienteDAO;
import com.bolsadeideas.spring.boot.backend.apirest.models.dao.iFacturaDAO;
import com.bolsadeideas.spring.boot.backend.apirest.models.dao.iProductosDAO;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Factura;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Producto;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Region;
@Service //Se define que es una clase servicio para poder utilizarlo como BEAN o componente
public class ClienteServiceImpl implements iClienteService {
	@Autowired //para no instanciar
	private iClienteDAO clienteDAO;
	
	@Autowired
	private iFacturaDAO facturaDAO;
	
	@Autowired
	private iProductosDAO productoDAO;
	
	@Transactional(readOnly = true) // se define que es una operacion transaccional solo de lectura
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDAO.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) { //recibe PAGE en vez de Cliente
		return clienteDAO.findAll(pageable); // se le pasa el aprametro pageable
	}

	@Transactional(readOnly=true)
	@Override
	public Cliente findById(Long id) {
		return clienteDAO.findById(id).get();/*el .get realiza la validacion automaticamente
		,Si no lo encuentra tira una exception de NOT found (Magia negra)*/
	}	
	@Transactional
	@Override
	public Cliente save(Cliente cliente) {
		return clienteDAO.save(cliente); //save recibe como parametro el cliente
	}
	@Transactional
	@Override
	public void delete(Long id) {
		clienteDAO.deleteById(id) ; // Busca el Cliente x el ID y lo borra
	}
	@Transactional
	@Override
	public List<Region> findAllRegion() {
		return clienteDAO.findAllRegion();
	}
	@Transactional(readOnly=true)	
	@Override
	public Factura findFacturaById(Long id) {
		return facturaDAO.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public Factura saveFactura(Factura factura) {
		return facturaDAO.save(factura);
	}
	@Transactional
	@Override
	public void deleteFacturaById(Long id) {
		facturaDAO.deleteById(id);
		
	}
	@Transactional(readOnly = true)
	@Override
	public List<Producto> findProductoByNombre(String term) {
		return productoDAO.findByNombreContainingIgnoreCase(term);
	}


} 
