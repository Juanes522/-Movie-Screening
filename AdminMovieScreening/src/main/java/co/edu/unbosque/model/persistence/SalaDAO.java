package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.SalaDTO;

public class SalaDAO {

	private ArrayList<SalaDTO> salas;

	public SalaDAO() {
		salas = obtenerSalas();
	}

	public ArrayList<SalaDTO> getSalas() {
		return salas;
	}

	public void setSalas(ArrayList<SalaDTO> salas) {
		this.salas = salas;
	}

	public void addSala(SalaDTO newSala) {

		JSONObject json = new JSONObject();
		json.put("id", newSala.getCine());

		JSONObject sala = new JSONObject();
		sala.put("sala", newSala.getNumeroSala());
		sala.put("aforo", newSala.getCapacidad());

		json.put("sala", sala);

		HttpClientSynchronous.doPost("http://localhost:8081/user/createSala", json.toString());

		salas = obtenerSalas();
	}

	public void removeSala(SalaDTO rem) {

		HttpClientSynchronous.doGet(
				"http://localhost:8081/user/deleteSala?numeroSala=" + rem.getNumeroSala() + "&id=" + rem.getCine());
		salas = obtenerSalas();

	}

	public ArrayList<SalaDTO> obtenerSalas() {

		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/getCine");
		ArrayList<SalaDTO> salas = new ArrayList<>();

		JSONArray jsonArray = new JSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject cineJson = jsonArray.getJSONObject(i);

			String nombreCine = cineJson.getString("id");

			if (cineJson.has("salas") && !cineJson.isNull("salas")) {
				JSONArray salasJson = cineJson.getJSONArray("salas");
				for (int j = 0; j < salasJson.length(); j++) {
					JSONObject salaJson = salasJson.getJSONObject(j);
					int numeroSala = salaJson.getInt("sala");
					int capacidad = salaJson.getInt("aforo");
					SalaDTO sala = new SalaDTO(nombreCine, numeroSala, capacidad);
					salas.add(sala);
				}
			}
		}

		return salas;
	}

}
