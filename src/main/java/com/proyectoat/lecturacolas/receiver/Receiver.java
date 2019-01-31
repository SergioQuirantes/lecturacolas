package com.proyectoat.lecturacolas.receiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);
	
	public void recibeMensaje(Object modeloPrueba) {
		
		ObjectMapper mapper = new ObjectMapper();
		ModeloPrueba modeloPrueba2 = mapper.convertValue(modeloPrueba, ModeloPrueba.class);
		
		System.out.println("Recibido <" + modeloPrueba2 + ">");
		latch.countDown();
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
