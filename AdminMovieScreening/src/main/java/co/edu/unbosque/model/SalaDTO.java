package co.edu.unbosque.model;

public class SalaDTO {

	private String cine;
	private int numeroSala;
	private int capacidad;

	public SalaDTO() {
	}

	public SalaDTO(String cine, int numeroSala, int capacidad) {
		super();
		this.cine = cine;
		this.numeroSala = numeroSala;
		this.capacidad = capacidad;
	}

	public String getCine() {
		return cine;
	}

	public void setCine(String cine) {
		this.cine = cine;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "SalaDTO [cine=" + cine + ", numeroSala=" + numeroSala + ", capacidad=" + capacidad + "]";
	}

}
