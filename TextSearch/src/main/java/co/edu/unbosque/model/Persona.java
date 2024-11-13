package co.edu.unbosque.model;

import java.util.List;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Personas")
public class Persona {

	@Id
	private String id;
	private String nacionalidad;
	private String sexo;
	private List<Premio> premios;

	public Persona() {
	}

	public Persona(String id, String nacionalidad, String sexo, List<Premio> premios) {
		super();
		this.id = id;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.premios = premios;
	}

	public List<Premio> getPremios() {
		return premios;
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
