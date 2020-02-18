package dev.controller.vm;

import java.time.LocalDateTime;

import dev.domain.Collegue;
import dev.domain.ReservationsSociete;
import dev.domain.VehiculeSociete;

public class ReservationsSocieteVM {

	private LocalDateTime date;
	private LocalDateTime dateRetour;
	private CollegueVM collegue;
	private CollegueVM chauffeur;
	private VehiculeSociete vehicules;
	
	public ReservationsSocieteVM(ReservationsSociete resaSociete) {
        this.date = resaSociete.getDate();
        this.dateRetour = resaSociete.getDateRetour();
        this.collegue = new CollegueVM(resaSociete.getCollegue());
        if(resaSociete.getChauffeur() != null) {
        	this.chauffeur = new CollegueVM(resaSociete.getChauffeur());
        }
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
	
	public CollegueVM getCollegue() {
		return collegue;
	}

	public void setCollegue(CollegueVM collegue) {
		this.collegue = collegue;
	}

	public CollegueVM getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(CollegueVM chauffeur) {
		this.chauffeur = chauffeur;
	}

	public VehiculeSociete getVehicules() {
		return vehicules;
	}
	public void setVehicules(VehiculeSociete vehicules) {
		this.vehicules = vehicules;
	}
}
