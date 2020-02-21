package dev.controller.dto;

import dev.domain.Categorie;
import dev.domain.Statut;

/***
 * classe utilisée par l API VehiculesSocieteController (méthode Post) pour passer les données à enregistrer : creation
 * @author audrey
 *
 */
public class VehiculesSocieteDTO {

	private String marque;
	private String modele;
	private String immatriculation;
	
	private Categorie categorie;
	private Statut statut;
	private String urlPhoto;
	
	public VehiculesSocieteDTO() {
		super();
	}
	
	/**
	 * @param marque
	 * @param modele
	 * @param immatriculation
	 * @param categorie
	 * @param statut
	 * @param urlPhoto
	 */
	public VehiculesSocieteDTO(String marque, String modele, String immatriculation, Categorie categorie,
			Statut statut, String urlPhoto) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.categorie = categorie;
		this.statut = statut;
		this.urlPhoto = urlPhoto;
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
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}
	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
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
	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	/**
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}
	/**
	 * @param urlPhoto the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}	
}
