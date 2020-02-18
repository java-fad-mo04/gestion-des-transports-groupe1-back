package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.vm.ReservationCovoiturageVM;
import dev.repository.ReservationsCovoiturageRepo;

@Service
public class ReservationCovoiturageService {

	private ReservationsCovoiturageRepo reservationsCovoiturageRepo;

	/**
	 * @param reservationsCovoiturageRepo
	 */
	public ReservationCovoiturageService(ReservationsCovoiturageRepo reservationsCovoiturageRepo) {
		super();
		this.reservationsCovoiturageRepo = reservationsCovoiturageRepo;
	}

	public List<ReservationCovoiturageVM> listerCovoiturages() {
		return this.reservationsCovoiturageRepo.findAll().stream().map(ReservationCovoiturageVM::new)
				.collect(Collectors.toList());

	}

}
