package co.edu.unbosque.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.primefaces.PrimeFaces;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.CertamenDTO;
import co.edu.unbosque.model.CineDTO;
import co.edu.unbosque.model.FestivalDTO;
import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.PremioDTO;
import co.edu.unbosque.model.ProyeccionDTO;
import co.edu.unbosque.model.SalaDTO;
import co.edu.unbosque.model.TrabajoDTO;
import co.edu.unbosque.model.persistence.CertamenDAO;
import co.edu.unbosque.model.persistence.CineDAO;
import co.edu.unbosque.model.persistence.FestivalDAO;
import co.edu.unbosque.model.persistence.PeliculaDAO;
import co.edu.unbosque.model.persistence.PersonaDAO;
import co.edu.unbosque.model.persistence.PremioDAO;
import co.edu.unbosque.model.persistence.ProyeccionDAO;
import co.edu.unbosque.model.persistence.SalaDAO;
import co.edu.unbosque.model.persistence.TrabajoDAO;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@ManagedBean
@RequestScoped
@Named
public class FileBean {

	// reportes
	private String cine;
	private String reporte;

	// cines
	private CineDAO cdao;
	private CineDTO selectedCine;
	private ArrayList<CineDTO> selectedCines;
	// salas
	private SalaDAO sdao;
	private SalaDTO selectedSala;
	private ArrayList<SalaDTO> selectedSalas;

	// peliculas
	private PeliculaDAO pdao;
	private PeliculaDTO selectedPeli;
	private ArrayList<PeliculaDTO> selectedPelis;

	// proyecciones
	private ProyeccionDAO rdao;
	private ProyeccionDTO selectedProy;
	private ArrayList<ProyeccionDTO> selectedPros;
	private String splitM;

	// festivales
	private FestivalDAO fdao;
	private FestivalDTO selectedFest;
	private ArrayList<FestivalDTO> selectedAles;

	// certamenes
	private CertamenDAO edao;
	private CertamenDTO selectedCer;
	private ArrayList<CertamenDTO> selectedCers;

	// trabajos
	private TrabajoDAO tdao;
	private TrabajoDTO selectedWork;
	private ArrayList<TrabajoDTO> selectedWorks;

	// personas
	private PersonaDAO odao;
	private PersonaDTO selectedPer;
	private ArrayList<PersonaDTO> selectedOns;

	// premio
	private PremioDAO adao;
	private PremioDTO selectedAwr;
	private ArrayList<PremioDTO> selectedAws;

	public FileBean() {
		cdao = new CineDAO();
		selectedCine = new CineDTO();
		selectedCines = new ArrayList<>();
		// salas
		sdao = new SalaDAO();
		selectedSalas = new ArrayList<>();
		selectedSala = new SalaDTO();

		// peliculas
		pdao = new PeliculaDAO();
		selectedPeli = new PeliculaDTO();
		selectedPelis = new ArrayList<>();

		// proyecciones
		rdao = new ProyeccionDAO();
		selectedProy = new ProyeccionDTO();
		selectedPros = new ArrayList<>();
		splitM = "";

		// festivales
		fdao = new FestivalDAO();
		selectedFest = new FestivalDTO();
		selectedAles = new ArrayList<>();

		// certamenes
		edao = new CertamenDAO();
		selectedCer = new CertamenDTO();
		selectedCers = new ArrayList<>();

		// trabajos
		tdao = new TrabajoDAO();
		selectedWork = new TrabajoDTO();
		selectedWorks = new ArrayList<>();

		// personas
		odao = new PersonaDAO();
		selectedPer = new PersonaDTO();
		selectedOns = new ArrayList<>();

		// premios
		adao = new PremioDAO();
		selectedAwr = new PremioDTO();
		selectedAws = new ArrayList<>();

		// reportes
		cine = "";
		reporte = "";

	}

	// cines
	public void setSelectedCines(ArrayList<CineDTO> selectedCines) {
		this.selectedCines = selectedCines;
	}

	public void openNew() {
		selectedCine = new CineDTO();
		selectedSala = new SalaDTO();
		selectedPeli = new PeliculaDTO();
		selectedProy = new ProyeccionDTO();
		splitM = "";
		selectedFest = new FestivalDTO();
		selectedCer = new CertamenDTO();
		selectedWork = new TrabajoDTO();
		selectedPer = new PersonaDTO();
		selectedAwr = new PremioDTO();
	}

	public boolean hasSelectedCines() {
		return this.selectedCine != null && !this.selectedCines.isEmpty();
	}

	public void saveCinema() {

		cdao.addCinema(selectedCine);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cinema Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageCinemaDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-cinema");
		PrimeFaces.current().ajax().update("form:dt-room");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteCinema() {

		cdao.deleteCinema(selectedCine);
		sdao = new SalaDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cinema Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteCinemaDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-cinema");
		PrimeFaces.current().ajax().update("form:dt-room");
		PrimeFaces.current().executeScript("location.reload();");

	}

	// salas

	public List<String> buscarNombres(String query) {
		List<String> resultados = new ArrayList<>();
		for (CineDTO cine : cdao.getCines()) {
			if (cine.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(cine.getId());
			}
		}
		return resultados;
	}

	public boolean hasSelectedSala() {
		return this.selectedSala != null && !this.selectedSalas.isEmpty();
	}

	public void saveSala() {

		sdao.addSala(selectedSala);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageRoomDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-room");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteSala() {

		sdao.removeSala(selectedSala);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('manageRoomDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-room");
		PrimeFaces.current().executeScript("location.reload();");
	}

	// peliculas

	public boolean hasSelectedMovies() {
		return this.selectedPeli != null && !this.selectedPelis.isEmpty();
	}

	public void saveMovie() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");

		selectedPeli.setId(uuid.substring(0, 4));
		pdao.addMovie(selectedPeli);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Movie Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageFilmDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-screening");
		PrimeFaces.current().ajax().update("form:dt-film");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteMovie() {

		pdao.removeMovie(selectedPeli);
		rdao = new ProyeccionDAO();
		tdao = new TrabajoDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Movie Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteFilmDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-screening");
		PrimeFaces.current().ajax().update("form:dt-work");
		PrimeFaces.current().ajax().update("form:dt-film");
		PrimeFaces.current().executeScript("location.reload();");

	}

	// proyecciones

	public boolean hasSelectedProy() {
		return this.selectedProy != null && !this.selectedPros.isEmpty();
	}

	public void saveProy() {
		String[] splited = splitM.split(" ");
		selectedProy.setName(splited[1]);
		selectedProy.setCip(splited[0]);
		selectedProy.setDiasEstreno(15);
		rdao.addProyeccion(selectedProy);
		rdao = new ProyeccionDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Screening Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageScreeningDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-screening");
		PrimeFaces.current().ajax().update("form:dt-film");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteProy() {

		rdao.removeProyeccion(selectedProy);
		rdao = new ProyeccionDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Screening Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteScreeningDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-screening");
		PrimeFaces.current().ajax().update("form:dt-film");
		PrimeFaces.current().executeScript("location.reload();");

	}

	public List<String> buscarCine(String query) {
		List<String> resultados = new ArrayList<>();
		for (CineDTO cine : cdao.getCines()) {
			if (cine.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(cine.getId());
			}
		}
		return resultados;
	}

	public List<Integer> buscarSalas(String query) {
		List<Integer> resultados = new ArrayList<>();
		if (sdao.getSalas() != null) {
			for (SalaDTO numero : sdao.getSalas()) {
				if (String.valueOf(numero.getNumeroSala()).contains(query)) {
					resultados.add(numero.getNumeroSala());
				}
			}
		}
		return resultados;
	}

	public List<String> buscarPeli(String query) {
		List<String> resultados = new ArrayList<>();
		for (PeliculaDTO peli : pdao.getPeliculas()) {
			if (peli.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(peli.getId() + " " + peli.getTitulo());
			}
		}
		return resultados;
	}

	// Festivales

	public boolean hasSelectedFest() {
		return this.selectedFest != null && !this.selectedAles.isEmpty();
	}

	public void saveFest() {
		fdao.addFestival(selectedFest);
		fdao = new FestivalDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Festival Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageFestivalDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-festival");
		PrimeFaces.current().ajax().update("form:dt-contest");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteFest() {

		fdao.removeFestival(selectedFest);
		fdao = new FestivalDAO();
		edao = new CertamenDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Screening Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteFestivalDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-festival");
		PrimeFaces.current().ajax().update("form:dt-contest");
		PrimeFaces.current().executeScript("location.reload();");

	}

	// certamenes

	public boolean hasSelectedCer() {
		return this.selectedCer != null && !this.selectedCers.isEmpty();
	}

	public void addCertamen() {
		edao.addCertamen(selectedCer);
		edao = new CertamenDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Certamen Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageContestDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-festival");
		PrimeFaces.current().ajax().update("form:dt-contest");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteCertamen() {
		edao.removeCertamen(selectedCer);
		edao = new CertamenDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Certamen Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteContestDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-festival");
		PrimeFaces.current().ajax().update("form:dt-contest");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public List<String> buscarFestival(String query) {
		List<String> resultados = new ArrayList<>();
		for (FestivalDTO fest : fdao.getFestivales()) {
			if (fest.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(fest.getId());
			}
		}
		return resultados;
	}

	// premios

	public boolean hasSelectedAwr() {
		return this.selectedAwr != null && !this.selectedAws.isEmpty();
	}

	public void addPremio() {
		String[] splited = splitM.split("-");

		selectedAwr.setId(splitM + "-" + selectedAwr.getPremio());
		selectedAwr.setFestival(splited[0]);
		selectedAwr.setCertamen(Integer.parseInt(splited[1]));

		adao.addPremio(selectedAwr);
		adao = new PremioDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Award Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageAwardDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-award");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deletePremio() {

		adao.removePremio(selectedAwr);
		adao = new PremioDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Award Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteAwardDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-award");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public List<String> buscarCertamen(String query) {
		List<String> resultados = new ArrayList<>();
		for (CertamenDTO c : edao.getCertamenes()) {
			if (c.getFestival().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(c.getFestival() + "-" + c.getAno());
			}
		}
		return resultados;
	}

	public List<String> buscarSencilla(String query) {
		List<String> resultados = new ArrayList<>();
		for (PeliculaDTO peli : pdao.getPeliculas()) {
			if (peli.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(peli.getId());
			}
		}
		return resultados;
	}

	public List<String> buscarPersona(String query) {
		List<String> resultados = new ArrayList<>();
		for (PersonaDTO peli : odao.getPersonas()) {
			if (peli.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(peli.getId());
			}
		}
		return resultados;
	}

	// trabajos

	public boolean hasSelectedWork() {
		return this.selectedWork != null && !this.selectedWorks.isEmpty();
	}

	public void addWork() {
		String[] splited = splitM.split(" ");
		selectedWork.setCip(splited[0]);
		selectedWork.setId(selectedWork.getCip() + "-" + selectedWork.getTarea());
		tdao.addWork(selectedWork);
		tdao = new TrabajoDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Work Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageWorkDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-work");
		PrimeFaces.current().ajax().update("form:dt-staff");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deleteWork() {

		tdao.removeWork(selectedWork);
		tdao = new TrabajoDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Work Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteWorkDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-work");
		PrimeFaces.current().ajax().update("form:dt-staff");
		PrimeFaces.current().executeScript("location.reload();");
	}

	// Personas

	public boolean hasSelectedPer() {
		return this.selectedPer != null && !this.selectedOns.isEmpty();
	}

	public void addPersona() {

		odao.addPersona(selectedPer);
		odao = new PersonaDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageStaffDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-work");
		PrimeFaces.current().ajax().update("form:dt-staff");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public void deletePersona() {

		odao.removePersona(selectedPer);
		odao = new PersonaDAO();
		adao = new PremioDAO();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person Removed Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteStaffDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-work");
		PrimeFaces.current().ajax().update("form:dt-staff");
		PrimeFaces.current().ajax().update("form:dt-award");
		PrimeFaces.current().executeScript("location.reload();");
	}

	public List<String> buscarTrabajo(String query) {
		List<String> resultados = new ArrayList<>();
		for (TrabajoDTO w : tdao.getTrabajos()) {
			if (w.getCip().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(w.getCip() + " " + w.getTarea());
			}
		}
		return resultados;
	}
	


	public void report1() {
		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/reporteProyeccionesCiudad");
		JSONArray jsonArray = new JSONArray(json);

		StringBuilder formattedText = new StringBuilder();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);

			formattedText.append("Ciudad: ").append(obj.getString("ciudad")).append("\n");

			JSONArray proyecciones = obj.getJSONArray("proyecciones");
			for (int j = 0; j < proyecciones.length(); j++) {
				JSONObject proyeccion = proyecciones.getJSONObject(j);

				formattedText.append("  Cine: ").append(proyeccion.getString("cine")).append("\n");
				formattedText.append("  Sala: ").append(proyeccion.getInt("sala")).append("\n");
				formattedText.append("  Fecha de Estreno: ").append(proyeccion.getString("fechaEstreno")).append("\n");
				formattedText.append("  Dias desde Estreno: ").append(proyeccion.getInt("diasEstreno")).append("\n");
				formattedText.append("  Espectadores: ").append(proyeccion.getInt("espectadores")).append("\n");
				formattedText.append("  Recaudación: ").append(proyeccion.getInt("recaudacion")).append("\n");
				formattedText.append("\n");
			}
		}

		reporte = formattedText.toString();

	}

	public void report2() {
		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/reportePremiosPersona");
		JSONArray jsonArray = new JSONArray(json);

		StringBuilder formattedText = new StringBuilder();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);

			formattedText.append("Persona: ").append(obj.getString("persona")).append("\n");
			formattedText.append("Pelicula: ").append(obj.getString("pelicula")).append("\n");
			formattedText.append("Premio: ").append(obj.getString("premio")).append("\n");
			formattedText.append("Festival: ").append(obj.getString("festival")).append("\n");
			formattedText.append("Certamen: ").append(obj.getInt("certamen")).append("\n");
			formattedText.append("\n");
		}

		reporte = formattedText.toString();
	}

	public void report3() {
		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/reportePeliculasDirector");

		JSONArray jsonArray = new JSONArray(json);

		StringBuilder formattedText = new StringBuilder();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);

			formattedText.append("Persona: ").append(obj.getString("persona")).append("\n");
			formattedText.append("Películas: ").append("\n");

			JSONArray peliculas = obj.getJSONArray("peliculas");
			for (int j = 0; j < peliculas.length(); j++) {
				formattedText.append("  - ").append(peliculas.getString(j)).append("\n");
			}
			formattedText.append("\n");
		}

		reporte = formattedText.toString();
	}

	public void report4() {
		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/reporteRecaudacionPromedio");
		JSONArray jsonArray = new JSONArray(json);

		StringBuilder textoReporte = new StringBuilder();
		textoReporte.append("Reporte de recaudación promedio por sala y cine:\n\n");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String cine = jsonObject.getString("cine");
			int sala = jsonObject.getInt("sala");
			double recaudacionPromedio = jsonObject.getDouble("recaudacionPromedio");

			textoReporte.append("Cine: " + cine + "\n").append("Sala: " + sala + "\n")
					.append("Recaudación Promedio: $" + recaudacionPromedio + "\n")
					.append("-------------------------------\n");
		}
		this.reporte = textoReporte.toString();
	}

	public void report5() {

		String json = HttpClientSynchronous.doGet("http://localhost:8081/user/reporteProyeccionesCine?nombre=" + cine);

		JSONArray proyecciones = new JSONArray(json);

		StringBuilder reporte = new StringBuilder();
		reporte.append("**Reporte de Proyecciones de Películas**\n\n");

		for (int i = 0; i < proyecciones.length(); i++) {
			JSONObject proyeccion = proyecciones.getJSONObject(i);

			String pelicula = proyeccion.getString("pelicula");
			int sala = proyeccion.getInt("sala");
			String fechaEstreno = proyeccion.getString("fechaEstreno");
			int diasEstreno = proyeccion.getInt("diasEstreno");
			int espectadores = proyeccion.getInt("espectadores");
			int recaudacion = proyeccion.getInt("recaudacion");

			reporte.append("1. Película: " + pelicula + "\n");
			reporte.append("   - Sala: " + sala + "\n");
			reporte.append("   - Fecha de Estreno: " + fechaEstreno + "\n");
			reporte.append("   - Días de Estreno: " + diasEstreno + "\n");
			reporte.append("   - Espectadores: " + espectadores + "\n");
			reporte.append("   - Recaudación: $" + recaudacion + "\n\n");
		}
		this.reporte = reporte.toString();

	}

	public void resetText() {
		reporte = "";
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public String getCine() {
		return cine;
	}

	public void setCine(String cine) {
		this.cine = cine;
	}

	public PremioDAO getAdao() {
		return adao;
	}

	public void setAdao(PremioDAO adao) {
		this.adao = adao;
	}

	public PremioDTO getSelectedAwr() {
		return selectedAwr;
	}

	public void setSelectedAwr(PremioDTO selectedAwr) {
		this.selectedAwr = selectedAwr;
	}

	public ArrayList<PremioDTO> getSelectedAws() {
		return selectedAws;
	}

	public void setSelectedAws(ArrayList<PremioDTO> selectedAws) {
		this.selectedAws = selectedAws;
	}

	public PersonaDAO getOdao() {
		return odao;
	}

	public void setOdao(PersonaDAO odao) {
		this.odao = odao;
	}

	public PersonaDTO getSelectedPer() {
		return selectedPer;
	}

	public void setSelectedPer(PersonaDTO selectedPer) {
		this.selectedPer = selectedPer;
	}

	public ArrayList<PersonaDTO> getSelectedOns() {
		return selectedOns;
	}

	public void setSelectedOns(ArrayList<PersonaDTO> selectedOns) {
		this.selectedOns = selectedOns;
	}

	public TrabajoDAO getTdao() {
		return tdao;
	}

	public void setTdao(TrabajoDAO tdao) {
		this.tdao = tdao;
	}

	public TrabajoDTO getSelectedWork() {
		return selectedWork;
	}

	public void setSelectedWork(TrabajoDTO selectedWork) {
		this.selectedWork = selectedWork;
	}

	public ArrayList<TrabajoDTO> getSelectedWorks() {
		return selectedWorks;
	}

	public void setSelectedWorks(ArrayList<TrabajoDTO> selectedWorks) {
		this.selectedWorks = selectedWorks;
	}

	public String getSplitM() {
		return splitM;
	}

	public void setSplitM(String splitM) {
		this.splitM = splitM;
	}

	public ProyeccionDAO getRdao() {
		return rdao;
	}

	public void setRdao(ProyeccionDAO rdao) {
		this.rdao = rdao;
	}

	public ProyeccionDTO getSelectedProy() {
		return selectedProy;
	}

	public void setSelectedProy(ProyeccionDTO selectedProy) {
		this.selectedProy = selectedProy;
	}

	public ArrayList<ProyeccionDTO> getSelectedPros() {
		return selectedPros;
	}

	public void setSelectedPros(ArrayList<ProyeccionDTO> selectedPros) {
		this.selectedPros = selectedPros;
	}

	public PeliculaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PeliculaDAO pdao) {
		this.pdao = pdao;
	}

	public PeliculaDTO getSelectedPeli() {
		return selectedPeli;
	}

	public void setSelectedPeli(PeliculaDTO selectedPeli) {
		this.selectedPeli = selectedPeli;
	}

	public ArrayList<PeliculaDTO> getSelectedPelis() {
		return selectedPelis;
	}

	public void setSelectedPelis(ArrayList<PeliculaDTO> selectedPelis) {
		this.selectedPelis = selectedPelis;
	}

	public SalaDAO getSdao() {
		return sdao;
	}

	public void setSdao(SalaDAO sdao) {
		this.sdao = sdao;
	}

	public SalaDTO getSelectedSala() {
		return selectedSala;
	}

	public void setSelectedSala(SalaDTO selectedSala) {
		this.selectedSala = selectedSala;
	}

	public ArrayList<SalaDTO> getSelectedSalas() {
		return selectedSalas;
	}

	public void setSelectedSalas(ArrayList<SalaDTO> selectedSalas) {
		this.selectedSalas = selectedSalas;
	}

	public CineDAO getCdao() {
		return cdao;
	}

	public void setCdao(CineDAO cdao) {
		this.cdao = cdao;
	}

	public CineDTO getSelectedCine() {
		return selectedCine;
	}

	public void setSelectedCine(CineDTO selectedCine) {
		this.selectedCine = selectedCine;
	}

	public ArrayList<CineDTO> getSelectedCines() {
		return selectedCines;
	}

	public FestivalDAO getFdao() {
		return fdao;
	}

	public void setFdao(FestivalDAO fdao) {
		this.fdao = fdao;
	}

	public FestivalDTO getSelectedFest() {
		return selectedFest;
	}

	public void setSelectedFest(FestivalDTO selectedFest) {
		this.selectedFest = selectedFest;
	}

	public ArrayList<FestivalDTO> getSelectedAles() {
		return selectedAles;
	}

	public void setSelectedAles(ArrayList<FestivalDTO> selectedAles) {
		this.selectedAles = selectedAles;
	}

	public CertamenDAO getEdao() {
		return edao;
	}

	public void setEdao(CertamenDAO edao) {
		this.edao = edao;
	}

	public CertamenDTO getSelectedCer() {
		return selectedCer;
	}

	public void setSelectedCer(CertamenDTO selectedCer) {
		this.selectedCer = selectedCer;
	}

	public ArrayList<CertamenDTO> getSelectedCers() {
		return selectedCers;
	}

	public void setSelectedCers(ArrayList<CertamenDTO> selectedCers) {
		this.selectedCers = selectedCers;
	}

}
