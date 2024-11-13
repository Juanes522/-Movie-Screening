package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

public class ResultReporte2 {

	private String persona;
	private List<String> peliculas;

	public ResultReporte2() {
		// TODO Auto-generated constructor stub
	}

	public ResultReporte2(String persona) {
		super();
		this.persona = persona;
		this.peliculas = new ArrayList<>();
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public List<String> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<String> peliculas) {
		this.peliculas = peliculas;
	}
	public void addPelicula(String pelicula) {
        this.peliculas.add(pelicula);
    }

}
