package dev.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ReservationsCovoiturage extends Reservations {

	private String depart;
	private String destination;
	
	@ManyToMany
	@JoinTable(name = "reservations_covoiturage_passager", joinColumns = @JoinColumn(name = "reservations_covoit_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "collegue_id", referencedColumnName = "id"))
	private List<Collegue> listePassagers;

	@ManyToOne
	@JoinColumn(name="ID_VEHICULE")
	private VehiculePerso vehicules;

	
	public ReservationsCovoiturage() {
		super();
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<Collegue> getListePassagers() {
		return listePassagers;
	}

	public void setListePassagers(List<Collegue> listePassagers) {
		this.listePassagers = listePassagers;
	}

	public VehiculePerso getVehicules() {
		return vehicules;
	}

	public void setVehicules(VehiculePerso vehicules) {
		this.vehicules = vehicules;
	}
	
}
