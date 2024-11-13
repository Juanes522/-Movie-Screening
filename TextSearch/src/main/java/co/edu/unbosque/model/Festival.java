package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.List;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Festivales")
public class Festival {

	@Id
	private String id;
	private LocalDate fundacion;
	private List<Certamen> certamenes;

	public Festival() {
		// TODO Auto-generated constructor stub
	}

	public Festival(String id, LocalDate fundacion, List<Certamen> certamenes) {
		super();
		this.id = id;
		this.fundacion = fundacion;
		this.certamenes = certamenes;
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

	public List<Certamen> getCertamenes() {
		return certamenes;
	}

	public void setCertamenes(List<Certamen> certamenes) {
		this.certamenes = certamenes;
	}

}
