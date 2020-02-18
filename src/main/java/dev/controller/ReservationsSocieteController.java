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

	public ReservationsSocieteController(ReservationsSocieteService reservationsSocieteService) {
		super();
		this.reservationsSocieteService = reservationsSocieteService;
	}


	@GetMapping
	public List<ReservationsSocieteVM> listerReservationsSociete() {
		return this.reservationsSocieteService.listerReservationsSociete();
	}

	@PostMapping
	public ResponseEntity<ReservationsSocieteVM> creerReservationSociete(@RequestBody @Valid ReservationsSocieteDTO resaPost) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.reservationsSocieteService.creerReservationSociete(resaPost));
	}
	
}
