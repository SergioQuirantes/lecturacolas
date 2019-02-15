package com.proyectoat.lecturacolas;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proyectoat.lecturacolas.model.Sensor;
import com.proyectoat.lecturacolas.service.CassandraService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LecturacolasApplicationTests {

	@Autowired
	private CassandraService cassandraService;
	
	/**
	 * Función de test de la base de datos
	 * */
	@Test
	public void contextLoads() {
		
		System.out.println("Intentando guardar modelos");
		
		System.out.println("Modelos guardado");
		
		//filtrado de los modelos por autor
		System.out.println("Los libros de Reverte son: ");
		
		System.out.println("Actualizaremos un libro");
		System.out.println("Los libros de Reverte son: ");
		
		//eliminacion de los modelos segun el autor
		System.out.println("El modelo se eliminara");
		System.out.println("Modelo borrado");
	}

}

