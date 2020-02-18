package dev.controller.dto;

import java.time.LocalDateTime;

import dev.controller.vm.CollegueVM;
import dev.domain.VehiculePerso;

public class ReservationsCovoiturageDTO {

	private LocalDateTime date;
	private String depart;
	private String destination;
	private CollegueVM chauffeur;
	private VehiculePerso vehicule;

	/**
	 * @param date
	 * @param depart
	 * @param destination
	 * @param chauffeur
	 * @param vehicule
	 */
	public ReservationsCovoiturageDTO() {

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

}
