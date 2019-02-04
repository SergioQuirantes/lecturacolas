package com.proyectoat.lecturacolas.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Repository("modelRepository")
public interface ModelRepository extends CassandraRepository<ModeloPrueba, UUID>{
	@AllowFiltering
	ModeloPrueba findByTitle(String titulo);
	
	@AllowFiltering
	List<ModeloPrueba> findByAutor(String autor);
	
	@Query("DELETE id, title, autor FROM cassandra_keyspace.modeloprueba WHERE title=?0")
	void deleteByTitle(String title);
	
	@AllowFiltering
	void deleteByAutor(String autor);
}
