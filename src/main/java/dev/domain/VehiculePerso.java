package dev.domain;

import java.util.Set;

import javax.persistence.Entity;

@Entity(name = "vehicule_perso")
public class VehiculePerso extends Vehicule {

	private int nombrePlace;

	// @OneToMany(mappedBy="vehicules")
	// private Set<ReservationsCovoiturage> reservationsCovoit = new HashSet
	// <ReservationsCovoiturage>();

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

//	public Set<ReservationsCovoiturage> getReservationsCovoit() {
//		return reservationsCovoit;
//	}
//
//	public void setReservationsCovoit(Set<ReservationsCovoiturage> reservationsCovoit) {
//		this.reservationsCovoit = reservationsCovoit;
//	}

}
