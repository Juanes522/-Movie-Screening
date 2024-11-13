package co.edu.unbosque.model;

public class RequestProyeccion {

	private String cip;
	private Proyeccion proyeccion;

	public RequestProyeccion() {
		// TODO Auto-generated constructor stub
	}

	public RequestProyeccion(String cip, Proyeccion proyeccion) {
		super();
		this.cip = cip;
		this.proyeccion = proyeccion;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public Proyeccion getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(Proyeccion proyeccion) {
		this.proyeccion = proyeccion;
	}

}
