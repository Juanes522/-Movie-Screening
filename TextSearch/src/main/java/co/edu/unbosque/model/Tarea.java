package co.edu.unbosque.model;

import dev.morphia.annotations.Entity;

@Entity("Tarea")
public class Tarea {

	private String id;
	private String nombre_Persona;
	private String tarea;
	private String cip;

	public Tarea() {
		// TODO Auto-generated constructor stub
	}

	public Tarea(String id, String nombre_Persona, String tarea, String cip) {
		super();
		this.id = id;
		this.nombre_Persona = nombre_Persona;
		this.tarea = tarea;
		this.cip = cip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getNombre_Persona() {
		return nombre_Persona;
	}

	public void setNombre_Persona(String nombre_Persona) {
		this.nombre_Persona = nombre_Persona;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

}
