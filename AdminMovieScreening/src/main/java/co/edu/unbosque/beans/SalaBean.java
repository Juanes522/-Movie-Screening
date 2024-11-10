package co.edu.unbosque.beans;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import co.edu.unbosque.model.CineDTO;
import co.edu.unbosque.model.SalaDTO;
import co.edu.unbosque.model.persistence.CineDAO;
import co.edu.unbosque.model.persistence.SalaDAO;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@ManagedBean
@RequestScoped
@Named
public class SalaBean {

	private SalaDAO sdao;
	private SalaDTO selectedSala;
	private ArrayList<SalaDTO> selectedSalas;
	private CineDAO cinemas;

	public SalaBean() {
		sdao = new SalaDAO();
		selectedSala = new SalaDTO();
		selectedSalas = new ArrayList<>();
		cinemas = new CineDAO();
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

	public List<String> traerCines() {
		List<String> names = new ArrayList<>();

		for (int i = 0; i < cinemas.getCines().size(); i++) {
			names.add(cinemas.getCines().get(i).getId());
		}

		return names;
	}

	public List<String> buscarNombres(String query) {
		List<String> resultados = new ArrayList<>();
		for (CineDTO cine : cinemas.getCines()) {
			if (cine.getId().toLowerCase().contains(query.toLowerCase())) {
				resultados.add(cine.getId());
			}
		}
		return resultados;
	}

	public void openNew() {
		selectedSala = new SalaDTO();
	}

	public boolean hasSelectedSala() {
		return this.selectedSala != null && !this.selectedSalas.isEmpty();
	}

	public void saveSala() {

		sdao.addSala(selectedSala);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageRoomDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-cinema");
	}

	public void deleteSala() {

		sdao.removeSala(selectedSala);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageRoomDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-cinema");
	}

}
