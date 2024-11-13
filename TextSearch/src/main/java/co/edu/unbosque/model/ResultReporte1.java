package co.edu.unbosque.model;

import dev.morphia.annotations.Entity;

@Entity("resultadosReporte")
public class ResultReporte1 {

	private String persona;
	private String pelicula;
	private String premio;
	private String festival;
	private int certamen;

	public ResultReporte1() {
		// TODO Auto-generated constructor stub
	}

	public ResultReporte1(String persona, String pelicula, String premio, String festival, int certamen) {
		super();
		this.persona = persona;
		this.pelicula = pelicula;
		this.premio = premio;
		this.festival = festival;
		this.certamen = certamen;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public int getCertamen() {
		return certamen;
	}

	public void setCertamen(int certamen) {
		this.certamen = certamen;
	}

}
