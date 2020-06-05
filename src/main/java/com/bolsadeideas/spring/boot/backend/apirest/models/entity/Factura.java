package com.bolsadeideas.spring.boot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="facturas")
public class Factura implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String observacion;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date CreateAt;
	 //muchas facturas pueden estar asociadas 1 cliente
	@JsonIgnoreProperties({"factura","hibernateLazyInitializer" , "handler"}) //ignora la contraparte de la relacion para no generar un loop infinito
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	@JsonIgnoreProperties(value={"hibernateLazyInitializer" , "handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<ItemFactura> items;

	
	
	
	public Factura() {
		items = new ArrayList<>();
		
	}

	@PrePersist
	public void prePersist() {
		this.CreateAt = new Date();
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for(ItemFactura item:items) {
			total += item.getImporte();
		}
		return total;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return CreateAt;
	}

	public void setCreateAt(Date createAt) {
		CreateAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}



	private static final long serialVersionUID = 1L;

}
