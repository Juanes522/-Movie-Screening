package co.edu.unbosque.model;

import java.time.LocalDate;

import dev.morphia.annotations.Entity;

@Entity("Proyeccion")
public class Proyeccion {

	private String cine;
	private int sala;
	private LocalDate fechaEstreno;
	private int diasEstreno;
	private int espectadores;
	private double recaudacion;

	public Proyeccion() {
		// TODO Auto-generated constructor stub
	}

	public Proyeccion(String cine, int sala, LocalDate fechaEstreno, int diasEstreno, int espectadores,
			double recaudacion) {
		super();
		this.cine = cine;
		this.sala = sala;
		this.fechaEstreno = fechaEstreno;
		this.diasEstreno = diasEstreno;
		this.espectadores = espectadores;
		this.recaudacion = recaudacion;
	}

	public String getCine() {
		return cine;
	}

	public void setCine(String cine) {
		this.cine = cine;
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

	public double getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(double recaudacion) {
		this.recaudacion = recaudacion;
	}

}
