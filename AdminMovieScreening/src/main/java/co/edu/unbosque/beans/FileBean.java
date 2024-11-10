package co.edu.unbosque.beans;

import java.util.ArrayList;

import org.primefaces.PrimeFaces;

import co.edu.unbosque.model.CineDTO;
import co.edu.unbosque.model.persistence.CineDAO;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@ManagedBean
@RequestScoped
@Named
public class FileBean {

	private CineDAO cdao;
	private CineDTO selectedCine;
	private ArrayList<CineDTO> selectedCines;

	public FileBean() {
		cdao = new CineDAO();
		selectedCine = new CineDTO();
		selectedCines = new ArrayList<>();
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

	public void setSelectedCines(ArrayList<CineDTO> selectedCines) {
		this.selectedCines = selectedCines;
	}

	public void openNew() {
		selectedCine = new CineDTO();
	}

	public boolean hasSelectedCines() {
		return this.selectedCine != null && !this.selectedCines.isEmpty();
	}

	public void saveCinema() {

		cdao.addCinema(selectedCine);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cinema Added Succesfully"));
		PrimeFaces.current().executeScript("PF('manageCinemaDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-cinema");
	}

	public void deleteCinema() {

		cdao.deleteCinema(selectedCine);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cinema Added Succesfully"));
		PrimeFaces.current().executeScript("PF('deleteCinemaDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-cinema");
	}

}
