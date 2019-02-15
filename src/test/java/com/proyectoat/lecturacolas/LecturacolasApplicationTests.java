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
		
	}

}

