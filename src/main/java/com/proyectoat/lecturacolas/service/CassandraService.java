package com.proyectoat.lecturacolas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		modelRepository.save(modeloPrueba);
	}
	
	
	public List<ModeloPrueba> getAll(){
		return modelRepository.findAll();
	}
	
	
	public ModeloPrueba getModeloPorTitulo(String titulo) {
		
		Optional<ModeloPrueba> modelo = modelRepository.findById(titulo);
		if(modelo.isPresent()) {
			return modelo.get();
		}
		return null;
	}
	
	public List<ModeloPrueba> getModeloPorAutor(String nombreAutor) {
		return modelRepository.findByAutor(nombreAutor);
	}
	
	
	public void updateModelo(ModeloPrueba modeloActualizado) {
		Optional<ModeloPrueba> modeloActualizable = modelRepository.findById(modeloActualizado.getTitle());
		
		if(modeloActualizable.isPresent()) {
			ModeloPrueba modelo = modeloActualizable.get();
			modelo.setAutor(modeloActualizado.getAutor());
			
			modelRepository.save(modelo);
		}
		
	}
	
	public void deleteAll() {
		modelRepository.deleteAll();
	}
	
	public void deleteModeloPorTitulo(String titulo) {
		modelRepository.deleteById(titulo);
	}
	
	public void deleteModeloPorAutor(String autor) {
		
		List<ModeloPrueba> librosAutor = getModeloPorAutor(autor);
		
		for(ModeloPrueba modeloPrueba : librosAutor) {
			modelRepository.deleteById(modeloPrueba.getTitle());
		}
	}
}
