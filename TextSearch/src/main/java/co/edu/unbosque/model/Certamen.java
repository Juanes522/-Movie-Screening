package co.edu.unbosque.model;

import dev.morphia.annotations.Entity;

@Entity("Certamen")
public class Certamen {

	private int ano;
	private String organizador;

	public Certamen() {
		// TODO Auto-generated constructor stub
	}

	public Certamen(int ano, String organizador) {
		super();
		this.ano = ano;
		this.organizador = organizador;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

}
