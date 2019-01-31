package com.proyectoat.lecturacolas.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.proyectoat.lecturacolas.model.ModeloPrueba;

@Repository
public interface ModelRepository extends CassandraRepository<ModeloPrueba, Long>{

}
