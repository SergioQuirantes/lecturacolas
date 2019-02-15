package com.proyectoat.lecturacolas.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.proyectoat.lecturacolas.model.Sensor;

@Repository("modelRepository")
public interface ModelRepository extends CassandraRepository<Sensor, String>{
			
}
