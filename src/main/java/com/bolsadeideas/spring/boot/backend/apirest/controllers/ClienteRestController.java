package com.bolsadeideas.spring.boot.backend.apirest.controllers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest.models.entity.Region;
import com.bolsadeideas.spring.boot.backend.apirest.models.services.iClienteService;
import com.bolsadeideas.spring.boot.backend.apirest.models.services.iUploadFileService;
@CrossOrigin(origins= {"http://localhost:4200","*"}) // CORS, permite conexion con la aplicacion ANGULAR
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private iClienteService clienteService;
	
	@Autowired
	private iUploadFileService uploadFileService;	
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	@GetMapping("/clientes")
	@ResponseStatus(HttpStatus.OK)/*Anotacion redundante, es la que viene por defecto en Spring
	Sirve para especificar el estatus de la respuesta cuando el metodo se ejecuta correctamente	*/
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		return clienteService.findAll(PageRequest.of(page, 4)); //Select all con paginacion
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/clientes/{id}") // Mapeo donde el ID sera Variable
	public ResponseEntity<?> SelectOne(@PathVariable Long id) {
		/*VALIDACION: Se reemplaza el tipo de dato cliente por ResponseEntity<?>, que es una
		 * clase que sirve para devolver excepciones, Mapea los posibles resultados y devuelve
		 * en forma d excepcion en caso de que no esten , la idea es que el programa no se caiga
		 * x niun lado y que cada posibilidad tenga su excepcion*/
		Cliente cliente = null;
		Map<String,Object> response	= new HashMap<>();
		try { //try carch para manejo de errores	
			cliente = clienteService.findById(id);	
		} 
		catch (NoSuchElementException e) { //error de not found
			
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage()+"|| El cliente con el id: "+id.toString()+" No se encuentra en la BD");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        } catch(DataAccessException e) {
        	response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage()+"|| El cliente con el id: "+id.toString()+" No se encuentra en la BD");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		
		
		/*Path variable sirve para marcar que el parametro se mostrara en la url de forma variable
		 En este caso la ID*/
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes") // Se usa post porque no recbie informacion , sino que envia (a la misma URL)
	@ResponseStatus(HttpStatus.CREATED) /*Para especificar que si el metodo funcion , el codigo
	de respuesta no es 200 de OK , sino 201 de CREADO (ya que es un metodo insert)
	*El bindingResult es un parametro de validacion para mostrar los errores que pueda tener el requestbody
	*El @Valid , valida el requestbody antes de ejecutar el create , valida las reglas puestas en la clase entity*/
	public ResponseEntity<?> create( @Valid @RequestBody Cliente cliente, BindingResult	 result ) {//Se indica que la peticion sera al Body de la WEB
		Map<String,Object> response	= new HashMap<>();
		Cliente clienteNuevo =  null;
		/*Si tiene errores creara un lista con esta mediante el comando getfielderrors
		 * luego con .stream() lo transformara a un string para que coincidan los tipos
		 * y va a mapear todos los errores con su campo y su mensaje , finalmente lo transforma
		 * a un tipo lista con .collect*/
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" +err.getField() +"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors",errors); 
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			
		}

		
		
		try {
			clienteNuevo = clienteService.save(cliente);
			
		} 	catch (NoSuchElementException e) { //error de not found
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            
           
        } catch(DataAccessException e) {
        	response.put("mensaje", "Error al insertar los datos");
            response.put("error", "Error al insertar el cliente\n"+" | "+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
       
        }	
		response.put("mensaje","El cliente "+ clienteNuevo.getNombre() + " ha sido creado con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);	
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}") //se usa PUT para updates
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody @Valid Cliente cliente,BindingResult result, @PathVariable Long id) {
		Map<String,Object> response	= new HashMap<>();
		Cliente	clienteActualizado = null;
		Cliente clienteActual = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" +err.getField() +"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors",errors); 
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try { //try carch para manejo de errores	
			 clienteActual = clienteService.findById(id);
		} 
		catch (NoSuchElementException e) { //error de not found
			
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", "|| El cliente con el id: "+id.toString()+" No se encuentra en la BD");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        } 
		 /*Se busca el cliente actual por su id
		El id no se modifica en un update*/
		if (clienteActual == null) {
			response.put("mensaje","No se puede Editar El cliente actual no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setApellido(cliente.getApellido()); //Se actualiza el resto de los datos
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			clienteService.save(clienteActual);
			//el metodo save es tan inteligente para saber cuando chucha queri hacer update o create
			//Es importante pasarle por parametro el cliente Actu
		} 	catch (NoSuchElementException e) { //error de not found
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            
           
        } catch(DataAccessException e) {
        	response.put("mensaje", "Error actualizar los datos");
            response.put("error", e.getMessage()+" | "+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
       
        }	
		response.put("mensaje","Cliente actualizado con exito");
		response.put("cliente",clienteActualizado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		

	}
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")

	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> response	= new HashMap<>();
		try { //try carch para manejo de errores	
			Cliente cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto(); 
			uploadFileService.eliminar(nombreFotoAnterior);
			clienteService.delete(id);
		} 
		catch (DataAccessException e) { //error de not found	
			
           response.put("mensaje", "Error al realizar la consulta a la base de datos");
           response.put("error", e.getMessage()+"|| El cliente con el id: "+id.toString()+" No se encuentra en la BD");
           
       } 
		response.put("mensaje","Cliente eliminado con Exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.ACCEPTED);
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"}) //PERMISOS
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id ){
		/*funcion para subir imagenes , param1 : un multipartfile (para archivos) y el nombre
		 * con una anotacion request param, y el id del cliente*/
		Map<String,Object> response	= new HashMap<>();
		Cliente cliente = clienteService.findById(id); // se ubica al cliente
		
		if (!file.isEmpty()) { 
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(file);
			} catch (IOException e) {
	        	response.put("mensaje", "Error actualizar los datos");
	        	response.put("Error"," "+ e.getMessage()+" "+e.getCause());
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//borrar la foto anterior
			String nombreFotoAnterior = cliente.getFoto();
			cliente.setFoto(nombreArchivo); // set foto
			clienteService.save(cliente); // se guarda el cliente
			
			response.put("cliente",cliente);
			response.put("mensaje","Imagen subida correctamente");
			
			
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	@GetMapping("/upload/img/{nombreFoto:.+}") // mostrar foto mas la extension
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Resource recurso= null;
		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders(); // algun dia lo entenderé
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+ recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);}
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegion();
	}
}

