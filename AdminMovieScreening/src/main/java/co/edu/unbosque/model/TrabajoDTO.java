package co.edu.unbosque.model;

public class TrabajoDTO {

	private String id;
	private String nombre_persona;
	private String tarea;
	private String cip;

	public TrabajoDTO() {
		// TODO Auto-generated constructor stub
	}

	public TrabajoDTO(String id, String nombre_persona, String tarea, String cip) {
		super();
		this.id = id;
		this.nombre_persona = nombre_persona;
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

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}

	@Override
	public String toString() {
		return "TrabajoDTO [id=" + id + ", nombre_persona=" + nombre_persona + ", tarea=" + tarea + ", cip=" + cip
				+ "]";
	}

}
