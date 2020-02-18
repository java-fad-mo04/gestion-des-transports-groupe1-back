package dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.controller.vm.ReservationsSocieteVM;
import dev.domain.ReservationsSociete;
import dev.repository.ReservationsSocieteRepo;

@Service
public class ReservationsSocieteService {

	private ReservationsSocieteRepo reservationsSocieteRepo;

	public ReservationsSocieteService(ReservationsSocieteRepo reservationsSocieteRepo) {
		super();
		this.reservationsSocieteRepo = reservationsSocieteRepo;
	}

	public List<ReservationsSocieteVM> listerReservationsSociete() {
		
		List<ReservationsSocieteVM> reservationsSocieteVM = new ArrayList<ReservationsSocieteVM>();
		for (ReservationsSociete resa : this.reservationsSocieteRepo.findAll()) {
			reservationsSocieteVM.add(new ReservationsSocieteVM(resa));

		}
		return reservationsSocieteVM;
	}
	
}
