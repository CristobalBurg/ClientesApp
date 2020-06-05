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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity //Clase entity de JPA 
@Table(name="cliente")
public class Cliente implements Serializable{

	@Id // Se indica que el id es la clave primaria
	@GeneratedValue(strategy=GenerationType.IDENTITY) // se genera identity cuando se autoincrementa
	private long id;
	@NotEmpty // validacion de javax de que el campo no puede estar vacio
	@Size(min=4, max=12) //validacion de javax para definir largo de caracteres
	@Column(nullable=false)
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	@Email // validacion de que debe tener formato email (javaBeans validator)
	@Column(nullable=false, unique=false)
	private String email;
	@NotNull(message="No puede estar vacio")
	@Column(name="create_at") // se identifica como columna , se puede omitir si se llaman igual
	@Temporal(TemporalType.DATE) //Se especifica que solo se usan DATE ,no time
	private Date createAt;
	private String foto;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY) // La region se carga solo cuando se solicita OPTIMIZACION
	@JoinColumn(name="region_id") // llave foranea
	@JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"}) // se ominte atributos que le da el LAZY a region pa que no tire errores
	private Region region;
	 
	@JsonIgnoreProperties(value={"hibernateLazyInitializer" , "handler", "cliente"} ,allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "cliente", cascade=CascadeType.ALL)
	private List<Factura> facturas;
	
	
	 
	 //@PrePersist // FECHA  evento del ciclo de vida que le asigna una valor a la variable antes de persistir

	public Cliente() {
		this.facturas = new ArrayList<>();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}


	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}


	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}