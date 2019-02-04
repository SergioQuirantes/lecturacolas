package com.proyectoat.lecturacolas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.proyectoat.lecturacolas.dao.ModelRepository;
import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Service("cassandraService")
public class CassandraService {

	private ModelRepository modelRepository;
	
	
	@Autowired
	public CassandraService(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}
	
	
	public void saveModelo(ModeloPrueba modeloPrueba) {
		modeloPrueba.setId(UUIDs.timeBased());
		modelRepository.save(modeloPrueba);
	}
	
	public ModeloPrueba getModeloPorTitulo(String titulo) {
		return modelRepository.findByTitle(titulo);
	}
	
	public List<ModeloPrueba> getModeloPorAutor(String nombreAutor) {
		return modelRepository.findByAutor(nombreAutor);
	}
	
	public void deleteModeloPorTitulo(String titulo) {
		modelRepository.deleteByTitle(titulo);
	}
	
	public void deleteModeloPorAutor(String autor) {
		modelRepository.deleteByAutor(autor);
	}
}
