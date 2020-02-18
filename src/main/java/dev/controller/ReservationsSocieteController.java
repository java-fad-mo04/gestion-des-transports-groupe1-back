package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.CollegueVM;
import dev.controller.vm.ReservationsSocieteVM;
import dev.domain.ReservationsSociete;
import dev.services.ReservationsSocieteService;


@RestController
@RequestMapping(value = "/reservationsSociete")
public class ReservationsSocieteController {

	private ReservationsSocieteService reservationsSocieteService;

	public ReservationsSocieteController(ReservationsSocieteService reservationsSocieteService) {
		super();
		this.reservationsSocieteService = reservationsSocieteService;
	}


	@GetMapping
	//public ResponseEntity<ReservationsSociete> listerReservationsSociete() {
	public List<ReservationsSocieteVM> listerReservationsSociete() {
		return this.reservationsSocieteService.listerReservationsSociete();
	}

}
