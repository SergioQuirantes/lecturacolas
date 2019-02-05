package com.proyectoat.lecturacolas;

import java.util.List;

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
		
		System.out.println(cassandraService.getModeloPorTitulo("Lore"));
		
		System.out.println("Intentando guardar modelos");
		cassandraService.saveModelo(new ModeloPrueba("alatriste", "Reverte"));
		cassandraService.saveModelo(new ModeloPrueba("Calendariode", "Adviento"));
		cassandraService.saveModelo(new ModeloPrueba("Reguau", "Reverte"));
		cassandraService.saveModelo(new ModeloPrueba("Toledana", "Reverte"));
		System.out.println("Modelos guardado");
		
		List<ModeloPrueba> lista = cassandraService.getModeloPorAutor("Reverte");
		System.out.println("Los libros de Reverte son: ");
		for(ModeloPrueba modelo : lista) {
			System.out.println(modelo);
		}
		
		System.out.println("Actualizaremos dos libros");
		cassandraService.updateModelo(new ModeloPrueba("Calendariode", "Revierte"));
		List<ModeloPrueba> lista2 = cassandraService.getModeloPorAutor("Reverte");
		System.out.println("Los libros de Reverte son: ");
		for(ModeloPrueba modelo : lista2) {
			System.out.println(modelo);
		}
		
		System.out.println("El modelo se eliminara");
		cassandraService.deleteModeloPorAutor("Reverte");
		System.out.println("Modelo borrado");
	}

}

