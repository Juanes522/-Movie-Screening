package co.edu.unbosque.model;

import java.util.List;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Peliculas")
public class Peliculas {

	@Id
	private String id;
	private String titulo;
	private int ano_produccion;
	private String nacionalidad;
	private int presupuesto;
	private int duracion;
	private List<Tarea> tareas;
	private List<Proyeccion> proyecciones;

	public Peliculas() {
		// TODO Auto-generated constructor stub
	}

	public Peliculas(String id, String titulo, int ano_produccion, String nacionalidad, int presupuesto, int duracion,
			List<Tarea> tareas, List<Proyeccion> proyecciones) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano_produccion = ano_produccion;
		this.nacionalidad = nacionalidad;
		this.presupuesto = presupuesto;
		this.duracion = duracion;
		this.tareas = tareas;
		this.proyecciones = proyecciones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno_produccion() {
		return ano_produccion;
	}

	public void setAno_produccion(int ano_produccion) {
		this.ano_produccion = ano_produccion;
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

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public List<Proyeccion> getProyecciones() {
		return proyecciones;
	}

	public void setProyecciones(List<Proyeccion> proyecciones) {
		this.proyecciones = proyecciones;
	}

}
