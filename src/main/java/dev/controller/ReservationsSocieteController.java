package dev.controller;

/***
 * controller get et post pour les reservations vehicules de societe
 * @return
 */
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.dto.ReservationsSocieteDTO;
import dev.controller.vm.ReservationsSocieteVM;
import dev.service.ReservationsSocieteService;


@RestController
@RequestMapping(value = "reservationsSociete")
public class ReservationsSocieteController {

	private ReservationsSocieteService reservationsSocieteService;
	
	/**
	 * 
	 * @param reservationsSocieteService
	 */
	public ReservationsSocieteController(ReservationsSocieteService reservationsSocieteService) {
		super();
		this.reservationsSocieteService = reservationsSocieteService;
	}

	/**
	 * GET reservationsSociete : méthode qui récupere les réservations de véhicules de société
	 * 
	 * @return liste de reservations de vehicules de societe (ReservationsSocieteVM)
	 */
	@GetMapping
	public List<ReservationsSocieteVM> listerReservationsSociete() {
		return this.reservationsSocieteService.listerReservationsSociete();
	}

	/**
	 * POST reservationsSociete : créer une réservation de vehicules de societe
	 * 
	 * @param resaPost : données json
	 * @return la reservation créée
	 */
	@PostMapping
	public ResponseEntity<ReservationsSocieteVM> creerReservationSociete(@RequestBody @Valid ReservationsSocieteDTO resaPost) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.reservationsSocieteService.creerReservationSociete(resaPost));
	}
	
}
