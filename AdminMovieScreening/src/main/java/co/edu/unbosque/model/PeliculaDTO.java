package co.edu.unbosque.model;

public class PeliculaDTO {

	private String id;
	private String titulo;
	private int ano_produccion;
	private String nacionalidad;
	private int presupuesto;
	private int duracion;

	public PeliculaDTO() {
		// TODO Auto-generated constructor stub
	}

	

	public PeliculaDTO(String id, String titulo, int ano_produccion, String nacionalidad, int presupuesto,
			int duracion) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano_produccion = ano_produccion;
		this.nacionalidad = nacionalidad;
		this.presupuesto = presupuesto;
		this.duracion = duracion;
	}




	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public int getAno_produccion() {
		return ano_produccion;
	}



	public void setAno_produccion(int ano_produccion) {
		this.ano_produccion = ano_produccion;
	}



	@Override
	public String toString() {
		return "PeliculaDTO [id=" + id + ", titulo=" + titulo + ", ano_produccion=" + ano_produccion + ", nacionalidad="
				+ nacionalidad + ", presupuesto=" + presupuesto + ", duracion=" + duracion + "]";
	}


}
