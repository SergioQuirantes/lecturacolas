package com.proyectoat.lecturacolas.receiver;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoat.lecturacolas.model.ModeloPrueba;
import com.proyectoat.lecturacolas.service.CassandraService;

@Component
public class Receiver {

	@Autowired
	private CassandraService cassandraService;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	public void recibeMensaje(Object modeloPrueba) {
		
		ObjectMapper mapper = new ObjectMapper();
		ModeloPrueba modeloPrueba2 = mapper.convertValue(modeloPrueba, ModeloPrueba.class);
		
		ModeloPrueba modeloBuscado = cassandraService.getModeloPorTitulo(modeloPrueba2.getTitle());
		
		if(modeloBuscado == null) {	
			System.out.println("Insercion");
			cassandraService.saveModelo(modeloPrueba2);			
		}
		else {
			System.out.println("Actualizacion");
			cassandraService.updateModelo(modeloPrueba2);
		}
		
		List<ModeloPrueba> lista = cassandraService.getAll();
		int count = lista.size();
		System.out.println("Datos de la base de datos");
		
		for(ModeloPrueba prueba : lista) {
			System.out.println("Recibido <" + prueba + ">");
		}
		
		System.out.println("\nModelos del autor: Reverte");
		lista = cassandraService.getModeloPorAutor("Reverte");
		for(ModeloPrueba prueba : lista) {
			System.out.println("Recibido <" + prueba + ">");
		}
		
		if(count > 5) {
			System.out.println("\nEliminacion de los datos");
			
			cassandraService.deleteModeloPorAutor("Reverte");
			System.out.println("Eliminados los libros de Reverte: ");
			
			lista = cassandraService.getAll();
			
			for(ModeloPrueba prueba : lista) {
				System.out.println("Recibido <" + prueba + ">");
			}
			
			cassandraService.deleteAll();
			System.out.println("\nBorrados todos los datos");
			System.out.println("" + cassandraService.getAll());
			
		}
		
		latch.countDown();
		
		
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
