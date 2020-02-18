package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.ReservationsCovoiturageDTO;
import dev.controller.vm.ReservationCovoiturageVM;
import dev.domain.Collegue;
import dev.domain.ReservationsCovoiturage;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepo;
import dev.repository.ReservationsCovoiturageRepo;

/**
 * Classe de service pour les méthodes utilisé par la classe
 * REservationsCovoiturageCOntroller
 *
 */
@Service
public class ReservationsCovoiturageService {

	private ReservationsCovoiturageRepo reservationsCovoiturageRepo;
	private CollegueRepo collegueRepo;

	/**
	 * @param reservationsCovoiturageRepo
	 */
	public ReservationsCovoiturageService(ReservationsCovoiturageRepo reservationsCovoiturageRepo,
			CollegueRepo collegueRepo) {
		super();
		this.reservationsCovoiturageRepo = reservationsCovoiturageRepo;
		this.collegueRepo = collegueRepo;
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

	public void ajouterPassager(long idColl, int idResa) throws CollegueNonTrouveException {
		Collegue col = this.collegueRepo.findById(idColl).orElseThrow(() -> new CollegueNonTrouveException(""));
		ReservationsCovoiturage resa = this.reservationsCovoiturageRepo.findById(idResa)
				.orElseThrow(() -> new CollegueNonTrouveException(""));
		resa.getListePassagers().add(col);
		this.reservationsCovoiturageRepo.ajouterPassager(resa, idResa);

	}

}
