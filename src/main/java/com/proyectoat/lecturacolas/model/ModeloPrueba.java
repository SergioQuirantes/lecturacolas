package com.proyectoat.lecturacolas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;


@Table(value = "modeloprueba")
public class ModeloPrueba {
	
	@Id
	@Column("title")
	private String title; //titulo de un libro
	
	@Column("autor")
	private String autor; //autor del libro
	
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
