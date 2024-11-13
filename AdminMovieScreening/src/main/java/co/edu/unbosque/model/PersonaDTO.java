package co.edu.unbosque.model;

public class PersonaDTO {

	private String id;
	private String nacionalidad;
	private String sexo;

	public PersonaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PersonaDTO(String id, String nacionalidad, String sexo) {
		super();
		this.id = id;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "PersonaDTO [id=" + id + ", nacionalidad=" + nacionalidad + ", sexo=" + sexo + "]";
	}

}
