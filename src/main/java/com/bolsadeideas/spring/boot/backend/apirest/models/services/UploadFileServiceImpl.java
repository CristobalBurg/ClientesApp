package com.bolsadeideas.spring.boot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements iUploadFileService {
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String DIRECTORIO_UPLOAD ="upload";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo  = getPath(nombreFoto) ; // se define el path
		log.info(rutaArchivo.toString()); // log
		Resource recurso = null;
		
			recurso = new UrlResource(rutaArchivo.toUri()); // creacion de la foto como un recurso
		
		
		if (!recurso.exists() && !recurso.isReadable()) { // validacion
			 rutaArchivo = Paths.get("src/main/resources/static/img").resolve("notuser.png").toAbsolutePath();
				try {
					recurso = new UrlResource(rutaArchivo.toUri()); // creacion de la foto como un recurso
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
			log.error("Error , no se pudo cargar la imagen "+ nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile file) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + "_"+file.getOriginalFilename().replace(" ","");
		//se obtiene su nombre original y se agrega un parte random para que no existan fotos con el mismo nombre
		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());
		/*se crea la variable de tipo path (java.files)se busca primero la carpeta
		 * luego el nombre del archivo y despues se calcula su ruta absoluta con : toabsolutepath*/
		
			Files.copy(file.getInputStream(),rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if (nombreFoto != null && nombreFoto.length()>0) {
			Path rutaFotoAnterior = getPath(nombreFoto);
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
			
		}
		return false;}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
