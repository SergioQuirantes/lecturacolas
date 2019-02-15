package com.proyectoat.lecturacolas.model;


import java.util.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


@Table(value = "sensor")
public class Sensor {
	
	@PrimaryKeyColumn(
			name = "nombre_sensor",
			ordinal = 0,
			type = PrimaryKeyType.PARTITIONED
			)
	private String nombreSensor;
	
	@PrimaryKeyColumn(
			name = "momento_creacion",
			ordinal = 1,
			type = PrimaryKeyType.PARTITIONED
			)
	private Date momentoCreacion = new Date(System.currentTimeMillis()); 
	
	@Column("humedad")
	private String humedad;
	
	@Column("temperatura")
	private String temperatura;
	
	@Column("dioxido_carbono")
	private String dioxido_carbono;
	
	@Column("monoxido_carbono")
	private String monoxido_carbono;
	
	
	public Sensor() {
	}

	public Sensor(String nombreSensor, String humedad, String temperatura, String dioxido_carbono,
			String monoxido_carbono) {
		this.nombreSensor = nombreSensor;
		this.humedad = humedad;
		this.temperatura = temperatura;
		this.dioxido_carbono = dioxido_carbono;
		this.monoxido_carbono = monoxido_carbono;
	}

	public String getNombreSensor() {
		return nombreSensor;
	}

	public Date getMomentoCreacion() {
		return momentoCreacion;
	}
	

	public String getHumedad() {
		return humedad;
	}

	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getDioxido_carbono() {
		return dioxido_carbono;
	}

	public void setDioxido_carbono(String dioxido_carbono) {
		this.dioxido_carbono = dioxido_carbono;
	}

	public String getMonoxido_carbono() {
		return monoxido_carbono;
	}

	public void setMonoxido_carbono(String monoxido_carbono) {
		this.monoxido_carbono = monoxido_carbono;
	}

	@Override
	public String toString() {
		return "Sensor [nombreSensor=" + nombreSensor + ", momentoCreacion=" + momentoCreacion + ", humedad=" + humedad
				+ ", temperatura=" + temperatura + ", dioxido_carbono=" + dioxido_carbono + ", monoxido_carbono="
				+ monoxido_carbono + "]";
	}		
	
}
