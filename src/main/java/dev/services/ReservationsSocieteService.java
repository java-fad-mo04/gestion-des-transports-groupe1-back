package dev.services;

import org.springframework.stereotype.Service;

import dev.repository.ReservationsSocieteRepo;

//@Service
public class ReservationsSocieteService {

	private ReservationsSocieteRepo reservationsSocieteRepo;

	public ReservationsSocieteRepo getReservationsSocieteRepo() {
		return reservationsSocieteRepo;
	}

	public void setReservationsSocieteRepo(ReservationsSocieteRepo reservationsSocieteRepo) {
		this.reservationsSocieteRepo = reservationsSocieteRepo;
	}
	
	
}
