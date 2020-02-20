package dev.controller.vm;

/***
 * classe utilisée pour l'affichage cote front via appel API
 */

import dev.domain.Categorie;
import dev.domain.Statut;
import dev.domain.VehiculeSociete;

public class VehiculeSocieteVM {

	private long id;
	private String marque;
	private String modele;
	private String immatriculation;
	
	private Categorie categorie;
	private Statut statut;
	private String urlPhoto;
	
	
	public VehiculeSocieteVM() {
		super();
	}

	/**
	 * @param id
	 * @param marque
	 * @param modele
	 * @param immatriculation
	 * @param categorie
	 * @param statut
	 * @param urlPhoto
	 */
	public VehiculeSocieteVM(VehiculeSociete vehiculeSociete) {
		super();
		this.id = vehiculeSociete.getId();
		this.marque = vehiculeSociete.getMarque();
		this.modele = vehiculeSociete.getModele();
		this.immatriculation = vehiculeSociete.getImmatriculation();
		this.categorie = vehiculeSociete.getCategorie();
		this.statut = vehiculeSociete.getStatut();
		this.urlPhoto = vehiculeSociete.getUrlPhoto();
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
