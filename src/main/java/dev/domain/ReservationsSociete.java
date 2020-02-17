package dev.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReservationsSociete extends Reservations {

	@ManyToOne
    @JoinColumn(name = "id_chauffeur")
    private Collegue chauffeur;
	
	@ManyToOne
	@JoinColumn(name="ID_VEHICULE")
	private VehiculeSociete vehicules;

	public ReservationsSociete() {
		super();
	}

	public Collegue getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Collegue chauffeur) {
		this.chauffeur = chauffeur;
	}
	
	public VehiculeSociete getVehicules() {
		return vehicules;
	}

	public void setVehicules(VehiculeSociete vehicules) {
		this.vehicules = vehicules;
	}
}
