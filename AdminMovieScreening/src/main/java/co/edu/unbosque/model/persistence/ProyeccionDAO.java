package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.ProyeccionDTO;

public class ProyeccionDAO {

	private ArrayList<ProyeccionDTO> proyecciones;

	public ProyeccionDAO() {
		proyecciones = extraerProyecciones();
	}

	public ArrayList<ProyeccionDTO> getProyecciones() {
		return proyecciones;
	}

	public void setProyecciones(ArrayList<ProyeccionDTO> proyecciones) {
		this.proyecciones = proyecciones;
	}

	public void removeProyeccion(ProyeccionDTO proy) {

		HttpClientSynchronous
				.doGet("http://localhost:8081/user/deleteProyeccion?Cine=" + proy.getCine() + "&numeroSala="
						+ proy.getSala() + "&fecha=" + proy.getFechaEstreno().toString() + "&id=" + proy.getCip());
		proyecciones = extraerProyecciones();
	}

	public void addProyeccion(ProyeccionDTO proyeccionDTO) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("cip", proyeccionDTO.getCip());

		JSONObject proyeccion = new JSONObject();
		proyeccion.put("cine", proyeccionDTO.getCine());
		proyeccion.put("sala", proyeccionDTO.getSala());
		proyeccion.put("fechaEstreno", proyeccionDTO.getFechaEstreno().format(DateTimeFormatter.ISO_LOCAL_DATE));
		proyeccion.put("diasEstreno", proyeccionDTO.getDiasEstreno());
		proyeccion.put("espectadores", proyeccionDTO.getEspectadores());
		proyeccion.put("recaudacion", proyeccionDTO.getRecaudacion());

		jsonObject.put("proyeccion", proyeccion);

		HttpClientSynchronous.doPost("http://localhost:8081/user/createProyeccion", jsonObject.toString());
		
	}

	public ArrayList<ProyeccionDTO> extraerProyecciones() {
		JSONArray peliculasJsonArray = new JSONArray(
				HttpClientSynchronous.doGet("http://localhost:8081/user/getPeliculas"));

		ArrayList<ProyeccionDTO> todasLasProyecciones = new ArrayList<>();

		for (int i = 0; i < peliculasJsonArray.length(); i++) {
			JSONObject peliculaJson = peliculasJsonArray.getJSONObject(i);

			if (peliculaJson.has("proyecciones") && peliculaJson.get("proyecciones") != null
					&& peliculaJson.getJSONArray("proyecciones").length() > 0) {
				JSONArray proyeccionesArray = peliculaJson.getJSONArray("proyecciones");

				for (int j = 0; j < proyeccionesArray.length(); j++) {
					JSONObject proyeccionJson = proyeccionesArray.getJSONObject(j);
					String cip = peliculaJson.getString("id");
					String titulo = peliculaJson.getString("titulo");
					String cine = proyeccionJson.getString("cine");
					int sala = proyeccionJson.getInt("sala");
					LocalDate fechaEstreno = LocalDate.parse(proyeccionJson.getString("fechaEstreno"),
							DateTimeFormatter.ISO_LOCAL_DATE);
					int diasEstreno = proyeccionJson.getInt("diasEstreno");
					int espectadores = proyeccionJson.getInt("espectadores");
					int recaudacion = proyeccionJson.getInt("recaudacion");

					ProyeccionDTO proyeccionDTO = new ProyeccionDTO(cip, titulo, cine, sala, fechaEstreno, diasEstreno,
							espectadores, recaudacion);
					todasLasProyecciones.add(proyeccionDTO);
				}
			}
		}

		return todasLasProyecciones;
	}
}
