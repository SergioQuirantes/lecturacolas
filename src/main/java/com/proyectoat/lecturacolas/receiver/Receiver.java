package com.proyectoat.lecturacolas.receiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);
	
	public void recibeMensaje(ModeloPrueba modeloPrueba) {
		System.out.println("Recibido <" + modeloPrueba + ">");
		latch.countDown();
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
