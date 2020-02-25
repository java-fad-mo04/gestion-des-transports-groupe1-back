package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.ReservationsSocieteDTO;
import dev.controller.vm.ReservationsSocieteVM;
import dev.domain.Collegue;
import dev.domain.ReservationsSociete;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepo;
import dev.repository.ReservationsSocieteRepo;

/**
 * Classe de service pour les méthodes utilisés par la classe
 * ReservationsSocieteController
 * - lister les réservations de véhicule de société
 * - créer une réservation  de véhicule de société avec ou sans chauffeur
 *
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

	public List<ReservationsSocieteVM> listerReservationsSociete(Long idCol) throws  CollegueNonTrouveException {
	
		Collegue col = this.collegueRepo.findById(idCol).orElseThrow(() -> new CollegueNonTrouveException(""));
		return this.reservationsSocieteRepo.findByCollegue(col).stream().map(ReservationsSocieteVM::new)
				.collect(Collectors.toList());
	}
	
	public ReservationsSocieteVM creerReservationSociete (ReservationsSocieteDTO resaPost) throws CollegueNonTrouveException {
		
		Collegue col = this.collegueRepo.findByEmail(resaPost.getCollegue().getEmail()).orElseThrow(() -> new CollegueNonTrouveException(""));
		
		Boolean existResa = this.reservationsSocieteRepo.findByDateDepartAndDateRetourByCollegue(resaPost.getDate(), resaPost.getDateRetour(), col);
		
		if(!existResa) {
			ReservationsSociete resa = new ReservationsSociete();
			resa.setDate(resaPost.getDate());
			resa.setDateRetour(resaPost.getDateRetour());		
			resa.setCollegue(col);
			resa.setVehicules(resaPost.getVehicule());
			resa.setAvecChauffeur(resaPost.getAvecChauffeur());
			
			ReservationsSocieteVM resaVM = new ReservationsSocieteVM(resa);
			this.reservationsSocieteRepo.save(resa);
			return resaVM;
		}
		else {
			return null;
		}
	}
}
