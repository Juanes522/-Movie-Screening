package co.edu.unbosque.model.persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.FestivalDTO;

//6.18
public class FestivalDAO {

	private ArrayList<FestivalDTO> festivales;

	public FestivalDAO() {
		festivales = parseFestivals();
	}

	public ArrayList<FestivalDTO> getFestivales() {
		return festivales;
	}

	public void setFestivales(ArrayList<FestivalDTO> festivales) {
		this.festivales = festivales;
	}

	public void addFestival(FestivalDTO fest) {
		JSONObject festivalJson = new JSONObject();
		festivalJson.put("id", fest.getId());
		festivalJson.put("fundacion", fest.getFundacion().format(DateTimeFormatter.ISO_LOCAL_DATE));

		HttpClientSynchronous.doPost("http://localhost:8081/user/createFestival", festivalJson.toString());
		festivales = parseFestivals();

	}

	public void removeFestival(FestivalDTO fest) {

		HttpClientSynchronous.doGet("http://localhost:8081/user/deleteFestival?name=" + fest.getId());
		festivales = parseFestivals();

	}

	public static ArrayList<FestivalDTO> parseFestivals() {
		String jsonString = HttpClientSynchronous.doGet("http://localhost:8081/user/getFestivales");

		ArrayList<FestivalDTO> festivalList = new ArrayList<>();

		JSONArray jsonArray = new JSONArray(jsonString);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject festivalObj = jsonArray.getJSONObject(i);

			LocalDate fundacionDate = LocalDate.parse(festivalObj.getString("fundacion"), formatter);

			String id = festivalObj.getString("id");

			FestivalDTO festivalDTO = new FestivalDTO(id, fundacionDate);
			festivalList.add(festivalDTO);
		}

		return festivalList;
	}

}
