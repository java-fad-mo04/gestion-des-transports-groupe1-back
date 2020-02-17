package dev.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity(name = "vehicule_societe")
public class VehiculeSociete extends Vehicule {

	@Enumerated(EnumType.STRING)
	private Categorie categorie;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	private String urlPhoto;

	@OneToMany(mappedBy = "vehicules")
	private Set<ReservationsSociete> reservations = new HashSet<ReservationsSociete>();
	
	/**
	 * 
	 */
	public VehiculeSociete() {
		super();
	}

	/**
	 * Getter
	 * 
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Setter
	 * 
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter
	 * 
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * Setter
	 * 
	 * @param statut
	 *            the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * Getter
	 * 
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}

	/**
	 * Setter
	 * 
	 * @param urlPhoto
	 *            the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public Set<ReservationsSociete> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationsSociete> reservations) {
		this.reservations = reservations;
	}

}
