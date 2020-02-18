package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.controller.dto.ReservationsSocieteDTO;
import dev.controller.vm.ReservationsSocieteVM;
import dev.domain.ReservationsSociete;
import dev.repository.CollegueRepo;
import dev.repository.ReservationsSocieteRepo;

/**
 * Classe de service pour les méthodes utilisés par la classe
 * ReservationsSocieteController
 * - lister les réservations de véhicule de société
 * - créer une réservation  de véhicule de société avec ou sans chauffeur
 */

@Service
public class ReservationsSocieteService {

	private ReservationsSocieteRepo reservationsSocieteRepo;
	private CollegueRepo collegueRepo;
	
	public ReservationsSocieteService(ReservationsSocieteRepo reservationsSocieteRepo, CollegueRepo collegueRepo) {
		super();
		this.reservationsSocieteRepo = reservationsSocieteRepo;
		this.collegueRepo = collegueRepo;
	}

	public List<ReservationsSocieteVM> listerReservationsSociete() {
		return this.reservationsSocieteRepo.findAll().stream().map(ReservationsSocieteVM::new)
				.collect(Collectors.toList());
	}
	
	public ReservationsSocieteVM creerReservationSociete (ReservationsSocieteDTO resaPost) {
		
		ReservationsSociete resa = new ReservationsSociete();
		resa.setDate(resaPost.getDate());
		resa.setDateRetour(resaPost.getDateRetour());
		
		
		resa.setCollegue(this.collegueRepo.findByEmail(resaPost.getCollegue().getEmail()).orElseThrow(() -> new EntityNotFoundException("collegue non trouvé")));
		if(resa.getChauffeur() != null) {
			resa.setChauffeur(this.collegueRepo.findByEmail(resaPost.getChauffeur().getEmail()).orElseThrow(() -> new EntityNotFoundException("chauffeur non trouvé")));
		}
		
		resa.setVehicules(resaPost.getVehicule());
		resa.setAvecChauffeur(resaPost.getAvecChauffeur());
		
		ReservationsSocieteVM resaVM = new ReservationsSocieteVM(resa);
		this.reservationsSocieteRepo.save(resa);
		return resaVM;
	}
}
