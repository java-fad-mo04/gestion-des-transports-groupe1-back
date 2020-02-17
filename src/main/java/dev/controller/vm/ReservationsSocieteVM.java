package dev.controller.vm;

import java.time.LocalDateTime;

import dev.domain.Collegue;
import dev.domain.ReservationsSociete;
import dev.domain.VehiculeSociete;

public class ReservationsSocieteVM {

	private LocalDateTime date;
	private LocalDateTime dateRetour;
	private Collegue collegue;
	private Collegue chauffeur;
	private VehiculeSociete vehicules;
	
	public ReservationsSocieteVM(ReservationsSociete resaSociete) {
        this.date = resaSociete.getDate();
        this.dateRetour = resaSociete.getDateRetour();
        this.collegue = resaSociete.getCollegue();
        this.chauffeur = resaSociete.getChauffeur();
        this.vehicules = resaSociete.getVehicules(); 
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public LocalDateTime getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDateTime dateRetour) {
		this.dateRetour = dateRetour;
	}
	public Collegue getCollegue() {
		return collegue;
	}
	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
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
