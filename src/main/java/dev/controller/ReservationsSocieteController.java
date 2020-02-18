package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	//public ResponseEntity<ReservationsSociete> listerReservationsSociete() {
	public List<ReservationsSocieteVM> listerReservationsSociete() {
		return this.reservationsSocieteService.listerReservationsSociete();
	}

}
