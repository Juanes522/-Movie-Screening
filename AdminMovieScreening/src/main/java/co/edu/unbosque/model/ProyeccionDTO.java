package co.edu.unbosque.model;

import java.time.LocalDate;

public class ProyeccionDTO {

	private String cip;
	private String name;
	private String cine;
	private int sala;
	private LocalDate fechaEstreno;
	private int diasEstreno;
	private int espectadores;
	private int recaudacion;

	public ProyeccionDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProyeccionDTO(String cip, String name, String cine, int sala, LocalDate fechaEstreno, int diasEstreno,
			int espectadores, int recaudacion) {
		super();
		this.cip = cip;
		this.name = name;
		this.cine = cine;
		this.sala = sala;
		this.fechaEstreno = fechaEstreno;
		this.diasEstreno = diasEstreno;
		this.espectadores = espectadores;
		this.recaudacion = recaudacion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
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

	public int getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(int recaudacion) {
		this.recaudacion = recaudacion;
	}

}
