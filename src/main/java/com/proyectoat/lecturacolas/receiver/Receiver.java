package com.proyectoat.lecturacolas.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Component
public class Receiver {

	
	@JmsListener(destination = "cola_mensajes")
	public void recibeMensaje(ModeloPrueba modeloPrueba) {
		System.out.println("Recibido <" + modeloPrueba + ">");
	}
	
}
