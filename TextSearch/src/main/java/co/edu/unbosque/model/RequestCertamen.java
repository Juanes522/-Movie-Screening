package co.edu.unbosque.model;

public class RequestCertamen {

	private String festival;
	private Certamen certamen;

	public RequestCertamen() {
		// TODO Auto-generated constructor stub
	}

	public RequestCertamen(String festival, Certamen certamen) {
		super();
		this.festival = festival;
		this.certamen = certamen;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public Certamen getCertamen() {
		return certamen;
	}

	public void setCertamen(Certamen certamen) {
		this.certamen = certamen;
	}

}
