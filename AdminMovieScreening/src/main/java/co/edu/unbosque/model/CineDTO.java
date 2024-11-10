package co.edu.unbosque.model;

public class CineDTO {

	private String id;
	private String ciudad;
	private String direccion;

	public CineDTO() {
		// TODO Auto-generated constructor stub
	}

	public CineDTO(String id, String ciudad, String direccion) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.direccion = direccion;
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

	@Override
	public String toString() {
		return "CineDTO [id=" + id + ", ciudad=" + ciudad + ", direccion=" + direccion + "]";
	}

}
