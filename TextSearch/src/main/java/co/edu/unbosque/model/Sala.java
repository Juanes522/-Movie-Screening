package co.edu.unbosque.model;

import dev.morphia.annotations.Entity;

@Entity("Sala")
public class Sala {

	private int sala;
	private int aforo;

	public Sala() {
		// TODO Auto-generated constructor stub
	}

	public Sala(int sala, int aforo) {
		super();
		this.sala = sala;
		this.aforo = aforo;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

}
