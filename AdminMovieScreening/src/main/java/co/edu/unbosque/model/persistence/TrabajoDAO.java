package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.TrabajoDTO;

public class TrabajoDAO {

	ArrayList<TrabajoDTO> trabajos = new ArrayList<>();

	public TrabajoDAO() {
		trabajos = parseTrabajo();
	}

	public ArrayList<TrabajoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(ArrayList<TrabajoDTO> trabajos) {
		this.trabajos = trabajos;
	}

	public void addWork(TrabajoDTO trabajo) {

		JSONObject work = new JSONObject();
		work.put("id", trabajo.getId());
		work.put("tarea", trabajo.getTarea());
		work.put("cip", trabajo.getCip());
		work.put("nombre_Persona", trabajo.getNombre_persona());

		HttpClientSynchronous.doPost("http://localhost:8081/user/createTrabajo", work.toString());
		trabajos = parseTrabajo();
	}

	public void removeWork(TrabajoDTO trabajo) {

		HttpClientSynchronous
				.doGet("http://localhost:8081/user/deleteTrabajo?cip=" + trabajo.getCip() + "&id=" + trabajo.getId());
		trabajos = parseTrabajo();
	}

	public ArrayList<TrabajoDTO> parseTrabajo() {

		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/getPeliculas");

		ArrayList<TrabajoDTO> trabajos = new ArrayList<>();

		JSONArray peliculasArray = new JSONArray(json);

		for (int i = 0; i < peliculasArray.length(); i++) {
			JSONObject pelicula = peliculasArray.getJSONObject(i);

			if (!pelicula.isNull("tareas")) {
				JSONArray tareasArray = pelicula.getJSONArray("tareas");

				for (int j = 0; j < tareasArray.length(); j++) {
					JSONObject tareaObj = tareasArray.getJSONObject(j);

					TrabajoDTO tarea = new TrabajoDTO(tareaObj.getString("id"), tareaObj.getString("nombre_Persona"),
							tareaObj.getString("tarea"), tareaObj.getString("cip"));

					trabajos.add(tarea);
				}
			}
		}
		return trabajos;
	}

}
