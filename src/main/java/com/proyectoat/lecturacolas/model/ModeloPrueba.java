package com.proyectoat.lecturacolas.model;

public class ModeloPrueba {
	
	private String title;

	public ModeloPrueba() {
	}

	public ModeloPrueba(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return String.format("Libro{titulo=%s}", getTitle());
	}
	
	
	
}
