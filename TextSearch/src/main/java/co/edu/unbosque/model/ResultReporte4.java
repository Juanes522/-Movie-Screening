package co.edu.unbosque.model;

import java.util.List;

public class ResultReporte4 {

	private String ciudad;
	private List<Proyeccion> proyecciones;

	public ResultReporte4() {
		// TODO Auto-generated constructor stub
	}

	public ResultReporte4(String ciudad, List<Proyeccion> proyecciones) {
		super();
		this.ciudad = ciudad;
		this.proyecciones = proyecciones;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Proyeccion> getProyecciones() {
		return proyecciones;
	}

	public void setProyecciones(List<Proyeccion> proyecciones) {
		this.proyecciones = proyecciones;
	}

}
