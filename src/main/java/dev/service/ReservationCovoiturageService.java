package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.ReservationsCovoiturageDTO;
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

	public List<ReservationCovoiturageVM> chercherCovoiturageAvecFiltre(ReservationsCovoiturageDTO resaDTO) {
		return this.reservationsCovoiturageRepo.findAll().stream()
				.filter(resa -> resaDTO.getDepart().equalsIgnoreCase(resa.getDepart())
						&& resaDTO.getDestination().equalsIgnoreCase(resa.getDestination())
						&& resa.getDate().getDayOfYear() >= resaDTO.getDate().getDayOfYear() - 2
						&& resa.getDate().getDayOfYear() <= resaDTO.getDate().getDayOfYear() + 2)
				.map(ReservationCovoiturageVM::new).collect(Collectors.toList());
	}

}
