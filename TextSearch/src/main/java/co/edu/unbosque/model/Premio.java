package co.edu.unbosque.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Premios")
public class Premio {

	@Id
	private String id;
	private String premio;
	private String nombre_persona;
	private String cip;
	private String festival;
	private int certamen;

	public Premio() {
		// TODO Auto-generated constructor stub
	}

	public Premio(String id, String premio, String nombre_persona, String cip, String festival, int certamen) {
		super();
		this.id = id;
		this.premio = premio;
		this.nombre_persona = nombre_persona;
		this.cip = cip;
		this.festival = festival;
		this.certamen = certamen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
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
