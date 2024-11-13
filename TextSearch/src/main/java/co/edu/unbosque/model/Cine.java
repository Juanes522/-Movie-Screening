package co.edu.unbosque.model;

import java.util.List;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Cines")
public class Cine {

	@Id
	private String id;
	private String ciudad;
	private String direccion;
	private List<Sala> salas;

	public Cine() {
		// TODO Auto-generated constructor stub
	}

	public Cine(String id, String ciudad, String direccion, List<Sala> salas) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.salas = salas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

}
