package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import com.google.gson.Gson;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.PremioDTO;

public class PremioDAO {

	private ArrayList<PremioDTO> premios;

	public PremioDAO() {
		premios = extraerPremios();
	}

	public ArrayList<PremioDTO> getPremios() {
		return premios;
	}

	public void setPremios(ArrayList<PremioDTO> premios) {
		this.premios = premios;
	}

	public void addPremio(PremioDTO premio) {

		String json = new Gson().toJson(premio);

		HttpClientSynchronous.doPost("http://localhost:8081/user/createPremio", json);

		premios = extraerPremios();

	}

	public void removePremio(PremioDTO premio) {

		HttpClientSynchronous.doGet(
				"http://localhost:8081/user/deletePremio?name=" + premio.getNombre_persona() + "&id=" + premio.getId());
		premios = extraerPremios();

	}

	public ArrayList<PremioDTO> extraerPremios() {

		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/getPersonas");
		ArrayList<PremioDTO> premios = new ArrayList<>();

		JSONArray personasArray = new JSONArray(json);

		for (int i = 0; i < personasArray.length(); i++) {
			JSONObject persona = personasArray.getJSONObject(i);

			if (!persona.isNull("premios")) {
				JSONArray premiosArray = persona.getJSONArray("premios");

				for (int j = 0; j < premiosArray.length(); j++) {
					JSONObject premioObj = premiosArray.getJSONObject(j);

					PremioDTO premio = new PremioDTO(premioObj.getString("id"), premioObj.getString("premio"),
							premioObj.getString("nombre_persona"), premioObj.getString("cip"),
							premioObj.getString("festival"), premioObj.getInt("certamen"));

					premios.add(premio);
				}
			}
		}

		return premios;
	}

}
