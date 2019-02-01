package com.proyectoat.lecturacolas.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


@Table
public class ModeloPrueba {
	
	@PrimaryKeyColumn(
			name = "ID",
			ordinal = 0,
			type = PrimaryKeyType.CLUSTERED,
			ordering = Ordering.DESCENDING
			)
	private UUID id;
	
	@PrimaryKeyColumn(
			name = "title",
			ordinal = 1,
			type = PrimaryKeyType.PARTITIONED
			)
	private String title;
	
	@Column("autor")
	private String autor;
	
	public ModeloPrueba() {
	}

	public ModeloPrueba(String title, String autor) {
		this.title = title;
		this.autor = autor;
	}

	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return String.format("Libro{id=%s, titulo=%s, autor=%s}", getId(), getTitle(), getAutor());
	}
	
	
	
}
