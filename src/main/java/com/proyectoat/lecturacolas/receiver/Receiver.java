package com.proyectoat.lecturacolas.receiver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoat.lecturacolas.model.ModeloPrueba;
import com.proyectoat.lecturacolas.service.CassandraService;

@Component
public class Receiver {

	@Autowired
	private CassandraService cassandraService; //Service ya existente en el contexto
	
	/**
	 * Función con la que se trata la recepción de los datos de la cola
	 * */
	public void recibeMensaje(Object modeloPrueba) {
		
		ObjectMapper mapper = new ObjectMapper();//objeto para hacer la conversión del formato la información del mensaje al modelo
		ModeloPrueba modeloPrueba2 = mapper.convertValue(modeloPrueba, ModeloPrueba.class); //traduce del formato del objeto al modelo
		
		ModeloPrueba modeloBuscado = cassandraService.getModeloPorTitulo(modeloPrueba2.getTitle()); //se busca si el objeto existe ya en la BD
		
		if(modeloBuscado == null) {	
			System.out.println("Insercion");
			cassandraService.saveModelo(modeloPrueba2);	// Si el objeto no existe, se inserta en la tabla
			System.out.println("Recibido <" + modeloPrueba2 + ">");
		}
		else {
			System.out.println("Actualizacion");
			cassandraService.updateModelo(modeloPrueba2); // Si el objeto existe, se actualiza con la nueva información
			System.out.println("Recibido <" + modeloPrueba2 + ">");
		}
		
	}
	
}
