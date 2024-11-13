package co.edu.unbosque.model;

import java.time.LocalDate;

public class FestivalDTO {

	private String id;
	private LocalDate fundacion;

	public FestivalDTO() {
		// TODO Auto-generated constructor stub
	}

	public FestivalDTO(String id, LocalDate fundacion) {
		super();
		this.id = id;
		this.fundacion = fundacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getFundacion() {
		return fundacion;
	}

	public void setFundacion(LocalDate fundacion) {
		this.fundacion = fundacion;
	}

	@Override
	public String toString() {
		return "FestivalDTO [id=" + id + ", fundacion=" + fundacion + "]";
	}

}
