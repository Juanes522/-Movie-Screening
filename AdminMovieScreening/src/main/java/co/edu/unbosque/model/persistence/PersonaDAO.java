package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.PersonaDTO;

public class PersonaDAO {

	private ArrayList<PersonaDTO> personas;

	public PersonaDAO() {

		personas = convertPersona();

	}

	public ArrayList<PersonaDTO> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<PersonaDTO> personas) {
		this.personas = personas;
	}

	public void addPersona(PersonaDTO newPersona) {


		JSONObject persona = new JSONObject();
		persona.put("id", newPersona.getId());
		persona.put("nacionalidad", newPersona.getNacionalidad());
		persona.put("sexo", newPersona.getSexo());


		HttpClientSynchronous.doPost("http://localhost:8081/user/createPersona", persona.toString());

		personas = convertPersona();

	}

	public void removePersona(PersonaDTO persona) {

		HttpClientSynchronous.doGet("http://localhost:8081/user/deletePersona?name=" + persona.getId());
		personas = convertPersona();

	}

	public static ArrayList<PersonaDTO> convertPersona() {
		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/getPersonas");

		JSONArray jsonArray = new JSONArray(json);

		ArrayList<PersonaDTO> personas = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject personaJson = jsonArray.getJSONObject(i);

			String id = personaJson.getString("id");
			String nacionalidad = personaJson.getString("nacionalidad");
			String sexo = personaJson.getString("sexo");

			PersonaDTO persona = new PersonaDTO(id, nacionalidad, sexo);

			personas.add(persona);

		}

		return personas;
	}

}
