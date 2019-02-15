package com.proyectoat.lecturacolas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoat.lecturacolas.dao.ModelRepository;
import com.proyectoat.lecturacolas.model.Sensor;


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
	public void saveModelo(Sensor sensor) {
		modelRepository.save(sensor);
	}
	
	/**
	 * Recupera todos los objetos de la Base de Datos
	 * 
	 * @return List<ModeloPrueba>
	 */
	public List<Sensor> getAll(){
		return modelRepository.findAll();
	}
	
	
	
	
	/**
	 * Actualiza la información de un modelo ya existente con la del objeto por parametro
	 * 
	 * @param modeloActualizado
	 */
	public void updateModelo(Sensor modeloActualizado) {
			
	}
	
	/**
	 * Borra todos los objetos de la base de datos
	 */
	public void deleteAll() {
		modelRepository.deleteAll();
	}
	
	
	
	
}
