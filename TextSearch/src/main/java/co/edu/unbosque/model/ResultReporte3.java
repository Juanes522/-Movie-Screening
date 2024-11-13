package co.edu.unbosque.model;

public class ResultReporte3 {

	private String cine;
	private int sala;
	private double recaudacionPromedio;

	public ResultReporte3() {
		// TODO Auto-generated constructor stub
	}

	public ResultReporte3(String cine, int sala, double recaudacionPromedio) {
		super();
		this.cine = cine;
		this.sala = sala;
		this.recaudacionPromedio = recaudacionPromedio;
	}

	public String getCine() {
		return cine;
	}

	public void setCine(String cine) {
		this.cine = cine;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public double getRecaudacionPromedio() {
		return recaudacionPromedio;
	}

	public void setRecaudacionPromedio(double recaudacionPromedio) {
		this.recaudacionPromedio = recaudacionPromedio;
	}

}
