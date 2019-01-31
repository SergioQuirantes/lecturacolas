package com.proyectoat.lecturacolas.model;

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
	private Long id;
	
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
		return String.format("Libro{titulo=%s, autor=%s}", getTitle(), getAutor());
	}
	
	
	
}
