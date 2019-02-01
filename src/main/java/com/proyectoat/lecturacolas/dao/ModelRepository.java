package com.proyectoat.lecturacolas.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Repository("modelRepository")
public interface ModelRepository extends CassandraRepository<ModeloPrueba, UUID>{
	ModeloPrueba findByTitle(String titulo);
	List<ModeloPrueba> findByAutor(String autor);
}
