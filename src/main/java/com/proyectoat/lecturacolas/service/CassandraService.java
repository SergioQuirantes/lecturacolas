package com.proyectoat.lecturacolas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoat.lecturacolas.dao.ModelRepository;
import com.proyectoat.lecturacolas.model.ModeloPrueba;


/**
 *Clase de servicio de la aplicación.
 * 
 * @author squirantes
 *
 */
@Service("cassandraService")
public class CassandraService {

	private ModelRepository modelRepository;
	
	/**
	 * El constructor cuya anotacion indica que debe de asignarse el modelRepository del contexto
	 * 
	 * @param modelRepository
	 * 
	 */
	@Autowired
	public CassandraService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}
	
	/**
	 * 
	 * Guarda un nuevo Modelo en la base de datos
	 * 
	 * @param modeloPrueba
	 *  
	 */
	public void saveModelo(ModeloPrueba modeloPrueba) {
		modelRepository.save(modeloPrueba);
	}
	
	/**
	 * Recupera todos los objetos de la Base de Datos
	 * 
	 * @return List<ModeloPrueba>
	 */
	public List<ModeloPrueba> getAll(){
		return modelRepository.findAll();
	}
	
	/**
	 * Busqueda de objeto por el atributo "titulo"
	 * 
	 * @param titulo
	 * @return ModeloPrueba o null si no existe objeto con ese titulo
	 */
	public ModeloPrueba getModeloPorTitulo(String titulo) {
		
		Optional<ModeloPrueba> modelo = modelRepository.findById(titulo); //busca el objeto por ID (el titulo es el ID del modelo)
		if(modelo.isPresent()) {
			return modelo.get(); //Si está el objeto de devuelve
		}
		return null; //si no, un objeto nulo
	}
	
	/**
	 * Recupera todos los objetos de la base de datos que tengan el valor de autor pasado por parametro
	 * 
	 * @param nombreAutor
	 * @return List<ModeloPrueba>
	 */
	public List<ModeloPrueba> getModeloPorAutor(String nombreAutor) {
		return modelRepository.findByAutor(nombreAutor);
	}
	
	/**
	 * Actualiza la información de un modelo ya existente con la del objeto por parametro
	 * 
	 * @param modeloActualizado
	 */
	public void updateModelo(ModeloPrueba modeloActualizado) {
		Optional<ModeloPrueba> modeloActualizable = modelRepository.findById(modeloActualizado.getTitle()); //buscamos primero si el objeto está en BD
		
		if(modeloActualizable.isPresent()) {
			ModeloPrueba modelo = modeloActualizable.get(); //si está, lo obtenemos
			modelo.setAutor(modeloActualizado.getAutor()); //le insetamos los nuevos datos
			
			modelRepository.save(modelo); //y lo guardamos
		}
		//si no está, no se hace nada		
	}
	
	/**
	 * Borra todos los objetos de la base de datos
	 */
	public void deleteAll() {
		modelRepository.deleteAll();
	}
	
	/**
	 * Borra el objeto de la base de datos con ese título
	 * 
	 * @param titulo
	 */
	public void deleteModeloPorTitulo(String titulo) {
		modelRepository.deleteById(titulo); //se borra por ID (el titulo es el ID del modelo)
	}
	
	/**
	 * Borra todos los objetos de la base de datos con el valor de autor pasado por parametro
	 * 
	 * @param autor
	 */
	public void deleteModeloPorAutor(String autor) {
		
		List<ModeloPrueba> librosAutor = getModeloPorAutor(autor); //obtiene todos los libros por autor
		
		for(ModeloPrueba modeloPrueba : librosAutor) {
			modelRepository.deleteById(modeloPrueba.getTitle()); //los borra 1 a 1 por ID
		}
	}
}
