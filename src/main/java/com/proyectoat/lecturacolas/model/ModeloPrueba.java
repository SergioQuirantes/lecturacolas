package com.proyectoat.lecturacolas.model;

public class ModeloPrueba {
	
	private String title;
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
