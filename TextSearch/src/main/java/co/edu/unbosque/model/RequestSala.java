package co.edu.unbosque.model;

public class RequestSala {

	private String id;
	private Sala sala;

	public RequestSala() {
		// TODO Auto-generated constructor stub
	}

	public RequestSala(String id, Sala sala) {
		super();
		this.id = id;
		this.sala = sala;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}
