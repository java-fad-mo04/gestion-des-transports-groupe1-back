package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ReservationsSociete extends Reservations {

	private LocalDateTime dateRetour;
	
	@ManyToOne
    @JoinColumn(name = "id_chauffeur")
    private Collegue chauffeur;
	
	@ManyToOne
	@JoinColumn(name="ID_VEHICULE")
	private VehiculeSociete vehicules;

	@Column(columnDefinition = "boolean default false", nullable= false)
	private Boolean avecChauffeur;
	
	public ReservationsSociete() {
		super();
	}
	
	public LocalDateTime getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDateTime dateRetour) {
		this.dateRetour = dateRetour;
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

	public Boolean getAvecChauffeur() {
		return avecChauffeur;
	}

	public void setAvecChauffeur(Boolean avecChauffeur) {
		this.avecChauffeur = avecChauffeur;
	}
	
}
