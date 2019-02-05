package com.proyectoat.lecturacolas.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Repository("modelRepository")
public interface ModelRepository extends CassandraRepository<ModeloPrueba, String>{
		
	@AllowFiltering
	List<ModeloPrueba> findByAutor(String autor);
	
}
