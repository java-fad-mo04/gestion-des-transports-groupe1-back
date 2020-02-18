package dev.domain;

import javax.persistence.Entity;

@Entity(name = "vehicule_perso")
public class VehiculePerso extends Vehicule {

	private int nombrePlace;

	/**
	 * 
	 */
	public VehiculePerso() {
		super();
	}

	/**
	 * Getter
	 * 
	 * @return the nombrePlace
	 */
	public int getNombrePlace() {
		return nombrePlace;
	}

	/**
	 * Setter
	 * 
	 * @param nombrePlace
	 *            the nombrePlace to set
	 */
	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

}
