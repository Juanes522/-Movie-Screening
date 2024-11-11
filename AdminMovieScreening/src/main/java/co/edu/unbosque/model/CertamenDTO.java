package co.edu.unbosque.model;

public class CertamenDTO {

	private String festival;
	private int ano;
	private String organizador;

	public CertamenDTO() {
		// TODO Auto-generated constructor stub
	}

	public CertamenDTO(String festival, int ano, String organizador) {
		super();
		this.festival = festival;
		this.ano = ano;
		this.organizador = organizador;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
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

	@Override
	public String toString() {
		return "CertamenDTO [festival=" + festival + ", ano=" + ano + ", organizador=" + organizador + "]";
	}

}
