package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import com.google.gson.Gson;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.CineDTO;

public class CineDAO {

	private ArrayList<CineDTO> cines;

	public CineDAO() {
		cines = obtenerCines();
	}

	public ArrayList<CineDTO> obtenerCines() {

		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/getCine");
		ArrayList<CineDTO> cines = new ArrayList<>();

		JSONArray jsonArray = new JSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject cineJson = jsonArray.getJSONObject(i);

			String id = cineJson.getString("id");
			String ciudad = cineJson.getString("ciudad");
			String direccion = cineJson.getString("direccion");

			CineDTO cine = new CineDTO(id, ciudad, direccion);
			cines.add(cine);
		}

		return cines;
	}

	public ArrayList<CineDTO> getCines() {
		return cines;
	}

	public void setCines(ArrayList<CineDTO> cines) {
		this.cines = cines;
	}

	public void addCinema(CineDTO newCine) {
		String json = new Gson().toJson(newCine);

		HttpClientSynchronous.doPost("http://localhost:8081/user/createCine", json);

		cines = obtenerCines();
	}

	public void deleteCinema(CineDTO ob) {

		HttpClientSynchronous.doGet("http://localhost:8081/user/deleteCine?name=" + ob.getId());
		cines = obtenerCines();

	}
}
