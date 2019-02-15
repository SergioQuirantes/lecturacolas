package com.proyectoat.lecturacolas.receiver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoat.lecturacolas.model.Sensor;
import com.proyectoat.lecturacolas.service.CassandraService;

@Component
public class Receiver {

	@Autowired
	private CassandraService cassandraService; //Service ya existente en el contexto
	
	/**
	 * Función con la que se trata la recepción de los datos de la cola
	 * */
	public void recibeMensaje(Object sensor) {
		
		ObjectMapper mapper = new ObjectMapper();//objeto para hacer la conversión del formato la información del mensaje al modelo
		Sensor sensor2 = mapper.convertValue(sensor, Sensor.class); //traduce del formato del objeto al modelo
		
		if(sensor2.getHumedad() == null) {
			sensor2.setHumedad("");
		}
		if(sensor2.getTemperatura() == null) {
			sensor2.setTemperatura("");
		}		
		if(sensor2.getDioxido_carbono() == null) {
			sensor2.setDioxido_carbono("");
		}
		if(sensor2.getMonoxido_carbono() == null) {
			sensor2.setMonoxido_carbono("");
		}
		System.out.println(sensor2);
		cassandraService.saveModelo(sensor2);
		
	}
	
}
