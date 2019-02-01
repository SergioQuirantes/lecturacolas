package com.proyectoat.lecturacolas.receiver;

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
		
		System.out.println("Recibido <" + modeloPrueba2 + ">");
		cassandraService.saveModelo(modeloPrueba2);
		latch.countDown();
		//System.out.println("Publicacion: " + cassandraService.getModeloPorAutor("alviento"));
		
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
