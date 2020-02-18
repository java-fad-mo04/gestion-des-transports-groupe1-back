package dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

		return this.reservationsSocieteRepo.findAll().stream().map(ReservationsSocieteVM::new)
				.collect(Collectors.toList());
	}
	
}
