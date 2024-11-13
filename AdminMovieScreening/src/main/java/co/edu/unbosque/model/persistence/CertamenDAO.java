package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.CertamenDTO;

public class CertamenDAO {

	private ArrayList<CertamenDTO> certamenes;

	public CertamenDAO() {
		certamenes = parseCertamenes();
	}

	public ArrayList<CertamenDTO> getCertamenes() {
		return certamenes;
	}

	public void setCertamenes(ArrayList<CertamenDTO> certamenes) {
		this.certamenes = certamenes;
	}

	public void addCertamen(CertamenDTO certamen) {

		JSONObject json = new JSONObject();
		json.put("festival", certamen.getFestival());

		JSONObject sala = new JSONObject();
		sala.put("ano", certamen.getAno());
		sala.put("organizador", certamen.getOrganizador());

		json.put("certamen", sala);

		HttpClientSynchronous.doPost("http://localhost:8081/user/createCertamen", json.toString());

		certamenes = parseCertamenes();
	}

	public void removeCertamen(CertamenDTO certamen) {

		HttpClientSynchronous.doGet("http://localhost:8081/user/deleteCertamen?name=" + certamen.getFestival() + "&ano="
				+ certamen.getAno());
		certamenes = parseCertamenes();

	}

	public static ArrayList<CertamenDTO> parseCertamenes() {

		String jsonString = HttpClientSynchronous.doGet("http://localhost:8081/user/getFestivales");
		ArrayList<CertamenDTO> certamenList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(jsonString);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject festivalObj = jsonArray.getJSONObject(i);

			JSONArray certamenesArray = festivalObj.optJSONArray("certamenes");

			if (certamenesArray != null) {
				String nombreFestival = festivalObj.getString("id");

				for (int j = 0; j < certamenesArray.length(); j++) {
					JSONObject certamenObj = certamenesArray.getJSONObject(j);

					int ano = certamenObj.getInt("ano");
					String organizador = certamenObj.getString("organizador");

					CertamenDTO certamenDTO = new CertamenDTO(nombreFestival, ano, organizador);
					certamenList.add(certamenDTO);
				}
			}
		}

		return certamenList;
	}

}
