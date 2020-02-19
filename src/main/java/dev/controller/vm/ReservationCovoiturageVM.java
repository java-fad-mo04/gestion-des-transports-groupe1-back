package dev.controller.vm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import dev.domain.ReservationsCovoiturage;
import dev.domain.VehiculePerso;

/**
 * Structure modèlisant une reservation covoiturage servant à communiquer avec
 * l'extérieur (WEB API).
 *
 */
public class ReservationCovoiturageVM {

	private LocalDateTime date;
	private String depart;
	private String destination;
	private VehiculePerso vehicule;
	private CollegueVM chauffeur;
	private List<CollegueVM> passagers;

	/**
	 * @param date
	 * @param depart
	 * @param destination
	 * @param vehicule
	 * @param chauffeur
	 * @param passagers
	 */
	public ReservationCovoiturageVM(ReservationsCovoiturage resa) {
		this.date = resa.getDate();
		this.depart = resa.getDepart();
		this.destination = resa.getDestination();
		this.vehicule = resa.getVehicules();
		this.chauffeur = new CollegueVM(resa.getCollegue());
		this.passagers = resa.getListePassagers().stream().map(CollegueVM::new).collect(Collectors.toList());
		this.vehicule.setNombrePlace(this.vehicule.getNombrePlace() - this.passagers.size());
	}

	/**
	 * Getter
	 * 
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Setter
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Getter
	 * 
	 * @return the depart
	 */
	public String getDepart() {
		return depart;
	}

	/**
	 * Setter
	 * 
	 * @param depart
	 *            the depart to set
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * Getter
	 * 
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Setter
	 * 
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Getter
	 * 
	 * @return the vehicule
	 */
	public VehiculePerso getVehicule() {
		return vehicule;
	}

	/**
	 * Setter
	 * 
	 * @param vehicule
	 *            the vehicule to set
	 */
	public void setVehicule(VehiculePerso vehicule) {
		this.vehicule = vehicule;
	}

	/**
	 * Getter
	 * 
	 * @return the chauffeur
	 */
	public CollegueVM getChauffeur() {
		return chauffeur;
	}

	/**
	 * Setter
	 * 
	 * @param chauffeur
	 *            the chauffeur to set
	 */
	public void setChauffeur(CollegueVM chauffeur) {
		this.chauffeur = chauffeur;
	}

	/**
	 * Getter
	 * 
	 * @return the passagers
	 */
	public List<CollegueVM> getPassagers() {
		return passagers;
	}

	/**
	 * Setter
	 * 
	 * @param passagers
	 *            the passagers to set
	 */
	public void setPassagers(List<CollegueVM> passagers) {
		this.passagers = passagers;
	}

}
