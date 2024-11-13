package co.edu.unbosque.model;

import java.time.LocalDate;

public class ResultReporte5 {

	private String pelicula;
	private int sala;
	private LocalDate fechaEstreno;
	private int diasEstreno;
	private int espectadores;
	private int recaudacion;

	public ResultReporte5() {
		// TODO Auto-generated constructor stub
	}

	public ResultReporte5(String pelicula, int sala, LocalDate fechaEstreno, int diasEstreno, int espectadores,
			int recaudacion) {
		super();
		this.pelicula = pelicula;
		this.sala = sala;
		this.fechaEstreno = fechaEstreno;
		this.diasEstreno = diasEstreno;
		this.espectadores = espectadores;
		this.recaudacion = recaudacion;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public int getDiasEstreno() {
		return diasEstreno;
	}

	public void setDiasEstreno(int diasEstreno) {
		this.diasEstreno = diasEstreno;
	}

	public int getEspectadores() {
		return espectadores;
	}

	public void setEspectadores(int espectadores) {
		this.espectadores = espectadores;
	}

	public int getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(int recaudacion) {
		this.recaudacion = recaudacion;
	}
	
	
	
}
