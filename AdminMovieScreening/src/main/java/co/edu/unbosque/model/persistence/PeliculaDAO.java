package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import com.google.gson.Gson;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.PeliculaDTO;

public class PeliculaDAO {

	private ArrayList<PeliculaDTO> peliculas;

	public PeliculaDAO() {
		peliculas = extraerPeliculas();
	}

	public ArrayList<PeliculaDTO> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<PeliculaDTO> peliculas) {
		this.peliculas = peliculas;
	}

	public void addMovie(PeliculaDTO peli) {

		String json = new Gson().toJson(peli);
		System.out.println(json);
		HttpClientSynchronous.doPost("http://localhost:8081/user/createMovie", json);
		peliculas = extraerPeliculas();

	}

	public void removeMovie(PeliculaDTO peli) {

		HttpClientSynchronous.doGet("http://localhost:8081/user/deletePelicula?name=" + peli.getId());
		peliculas = extraerPeliculas();
	}

	public ArrayList<PeliculaDTO> extraerPeliculas() {
		ArrayList<PeliculaDTO> peliculas = new ArrayList<>();

		JSONArray jsonArray = new JSONArray(HttpClientSynchronous.doGet("http://localhost:8081/user/getPeliculas"));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);

			String nombre = json.optString("id");
			String titulo = json.optString("titulo");
			int ano = json.optInt("ano_produccion");
			String nacionalidad = json.optString("nacionalidad");
			int presupuesto = json.optInt("presupuesto");
			int duracion = json.optInt("duracion");

			PeliculaDTO peliculaDTO = new PeliculaDTO(nombre, titulo, ano, nacionalidad, presupuesto, duracion);
			peliculas.add(peliculaDTO);
		}

		return peliculas;
	}

}
