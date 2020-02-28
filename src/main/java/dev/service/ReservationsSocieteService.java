package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.ReservationsSocieteDTO;
import dev.controller.vm.ReservationsSocieteVM;
import dev.domain.Collegue;
import dev.domain.ReservationsSociete;
import dev.domain.VehiculeSociete;
import dev.exception.CollegueNonTrouveException;
import dev.exception.FormErrorException;
import dev.exception.VehiculeNonTrouveException;
import dev.repository.CollegueRepo;
import dev.repository.ReservationsSocieteRepo;
import dev.repository.VehiculeSocieteRepo;

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
	private VehiculeSocieteRepo vehiculeSocieteRepo;
	
	public ReservationsSocieteService(ReservationsSocieteRepo reservationsSocieteRepo, CollegueRepo collegueRepo, VehiculeSocieteRepo vehiculeSocieteRepo) {
		super();
		this.reservationsSocieteRepo = reservationsSocieteRepo;
		this.collegueRepo = collegueRepo;
		this.vehiculeSocieteRepo = vehiculeSocieteRepo;
	}

	public List<ReservationsSocieteVM> listerReservationsSociete(Long idCol) throws  CollegueNonTrouveException {
	
		Collegue col = this.collegueRepo.findById(idCol).orElseThrow(() -> new CollegueNonTrouveException(""));
		return this.reservationsSocieteRepo.findByCollegue(col).stream().map(ReservationsSocieteVM::new)
				.collect(Collectors.toList());
	}
	
	public ReservationsSocieteVM creerReservationSociete (ReservationsSocieteDTO resaPost) throws CollegueNonTrouveException,VehiculeNonTrouveException, FormErrorException {
		
		Collegue col = this.collegueRepo.findByEmail(resaPost.getCollegue().getEmail()).orElseThrow(() -> new CollegueNonTrouveException(""));
		VehiculeSociete vehicule = this.vehiculeSocieteRepo.findById(resaPost.getVehicule().getId()).orElseThrow(() -> new VehiculeNonTrouveException(""));
		
		Boolean existResa = this.reservationsSocieteRepo.findByDateDepartAndDateRetourByCollegue(resaPost.getDate(), resaPost.getDateRetour(), col);
		
		if(resaPost.getDateRetour().isBefore(resaPost.getDate())) {
			throw new FormErrorException("La date de retour doit être supérieure à la date de départ");
		}
		
		if(!existResa) {
			ReservationsSociete resa = new ReservationsSociete();
			resa.setDate(resaPost.getDate());
			resa.setDateRetour(resaPost.getDateRetour());		
			resa.setCollegue(col);
			resa.setVehicules(vehicule);
			if(resaPost.getAvecChauffeur()!= null) {
				resa.setAvecChauffeur(resaPost.getAvecChauffeur());
			} else {
				resa.setAvecChauffeur(false);
			}
			
			ReservationsSocieteVM resaVM = new ReservationsSocieteVM(resa);
			this.reservationsSocieteRepo.save(resa);
			return resaVM;
		}
		else {
			throw new FormErrorException("Une réservation est déjà existente à cette période");
		}
	}
	
	
	public List<ReservationsSocieteVM> listerReservationParVehicule(Long idVehicule) throws VehiculeNonTrouveException {
		
		VehiculeSociete vehicule = this.vehiculeSocieteRepo.findById(idVehicule).orElseThrow(() -> new VehiculeNonTrouveException(""));
		
		return this.reservationsSocieteRepo.findReservationsByVehicules(vehicule).stream().map(ReservationsSocieteVM::new)
		.collect(Collectors.toList());
	}
}
