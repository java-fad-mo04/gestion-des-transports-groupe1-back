package dev.controller.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import dev.controller.vm.CollegueVM;
import dev.domain.VehiculeSociete;

/***
 * classe utilisée par l API ReservationsSocieteController (methode Post) pour passer les données à enregistrer
 * @author audrey
 *
 */

public class ReservationsSocieteDTO {

	@NotNull
	@Future
	private LocalDateTime date;
	
	@NotNull
	@Future
	private LocalDateTime dateRetour;
	
	@NotNull
	private CollegueVM collegue;
	private CollegueVM chauffeur;
	
	@NotNull
	private VehiculeSociete vehicule;
	private Boolean avecChauffeur;
	
	/**
	 * 
	 * @param date
	 * @param dateRetour
	 * @param collegue
	 * @param chauffeur
	 * @param vehicule
	 * @param avecChauffeur
	 */

	public ReservationsSocieteDTO() {
		
	}
			
	public ReservationsSocieteDTO(LocalDateTime date, LocalDateTime dateRetour, CollegueVM collegue,
			CollegueVM chauffeur, VehiculeSociete vehicule, Boolean avecChauffeur) {
		super();
		this.date = date;
		this.dateRetour = dateRetour;
		this.collegue = collegue;
		this.chauffeur = chauffeur;
		this.vehicule = vehicule;
		this.avecChauffeur = avecChauffeur;
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

	public VehiculeSociete getVehicule() {
		return vehicule;
	}

	public void setVehicule(VehiculeSociete vehicule) {
		this.vehicule = vehicule;
	}

	public Boolean getAvecChauffeur() {
		return avecChauffeur;
	}

	public void setAvecChauffeur(Boolean avecChauffeur) {
		this.avecChauffeur = avecChauffeur;
	}
}
