package com.proyectoat.lecturacolas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proyectoat.lecturacolas.model.ModeloPrueba;
import com.proyectoat.lecturacolas.service.CassandraService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LecturacolasApplicationTests {

	@Autowired
	private CassandraService cassandraService;
	
	
	@Test
	public void contextLoads() {
		System.out.println("Intentando guardar modelo");
		cassandraService.saveModelo(new ModeloPrueba("alatriste", "Reverte"));
		System.out.println("Modelo guardado");
		//System.out.println("El modelo guardado es: " + cassandraService.getModeloPorTitulo("alatriste"));
		System.out.println("El modelo se eliminara");
		cassandraService.deleteModeloPorTitulo("alatriste");
		System.out.println("Modelo borrado");
	}

}

