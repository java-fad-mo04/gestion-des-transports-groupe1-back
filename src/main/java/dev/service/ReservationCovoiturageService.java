package dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.controller.vm.ReservationCovoiturageVM;
import dev.domain.ReservationsCovoiturage;
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
		List<ReservationsCovoiturage> reservationsCovoiturage = this.reservationsCovoiturageRepo.findAll();
		List<ReservationCovoiturageVM> reservationsVM = new ArrayList<ReservationCovoiturageVM>();
		for (ReservationsCovoiturage resa : reservationsCovoiturage) {
			reservationsVM.add(new ReservationCovoiturageVM(resa));

		}
		return reservationsVM;

	}

}
