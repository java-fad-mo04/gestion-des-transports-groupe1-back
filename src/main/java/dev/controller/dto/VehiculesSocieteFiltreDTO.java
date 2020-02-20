package dev.controller.dto;

/***
 * classe utilisée par l API VehiculesSocieteController (méthode Post) pour passer les données pour le filtre (recherche vehicule societe)
 * @author audrey
 *
 */

public class VehiculesSocieteFiltreDTO {

	
	private String marque;
	private String immatriculation;
	
	public VehiculesSocieteFiltreDTO() {
		super();
	}
	/**
	 * @param marque
	 * @param immatriculation
	 */
	public VehiculesSocieteFiltreDTO(String marque, String immatriculation) {
		super();
		this.marque = marque;
		this.immatriculation = immatriculation;
	}
	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}
	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}
	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}
	/**
	 * @param immatriculation the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
}
