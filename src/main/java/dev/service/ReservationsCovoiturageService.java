package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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

	/**
	 * permet d'instancier le repository ReservationCovoiturageRepo
	 */
	private ReservationsCovoiturageRepo reservationsCovoiturageRepo;
	/**
	 * permet d'instancier le repository ColleRepo
	 */
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

	/**
	 * Méthode permettant de lister tous les covoiturages annocés par un
	 * collegue
	 * 
	 * @param id
	 *            id du collègue ayant publié l'annonce
	 * @return liste de covoiturages
	 */
	public List<ReservationCovoiturageVM> listerCovoiturages(long id) {
		Collegue col = this.collegueRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("collegue non trouvé"));
		return this.reservationsCovoiturageRepo.findByCollegue(col).stream().map(ReservationCovoiturageVM::new)
				.collect(Collectors.toList());

	}

	/**
	 * Méthode permettant de lister les covoiturages reservés par un collègue
	 * 
	 * @param id
	 *            du collègue
	 * @return lise de ces covoiturages reservés
	 */
	public List<ReservationCovoiturageVM> listerCovoiturageReservés(long id) {
		return this.reservationsCovoiturageRepo.listerParPassager(id).stream().map(ReservationCovoiturageVM::new)
				.collect(Collectors.toList());
	}

	/**
	 * Cette méthode permet d'appliquer un filtre lorsqu'un collegeu cherche un
	 * covoiturage
	 * 
	 * @param resaDTO
	 * @return liste des covoiturages filtrés par date destination et départ
	 */
	public List<ReservationCovoiturageVM> chercherCovoiturageAvecFiltre(ReservationsCovoiturageDTO resaDTO) {
		return this.reservationsCovoiturageRepo.findAll().stream()
				.filter(resa -> resaDTO.getDepart().equalsIgnoreCase(resa.getDepart())
						&& resaDTO.getDestination().equalsIgnoreCase(resa.getDestination())
						&& resa.getDate().getDayOfYear() >= resaDTO.getDate().getDayOfYear() - 2
						&& resa.getDate().getDayOfYear() <= resaDTO.getDate().getDayOfYear() + 2)
				.map(ReservationCovoiturageVM::new).collect(Collectors.toList());
	}

	/**
	 * cette méthode permet d'ajouter un passager à un covoiturage programmé par
	 * un autre cillègue (idColl)
	 * 
	 * @param idColl
	 * @param idResa
	 * @throws CollegueNonTrouveException
	 */
	public void ajouterPassager(long idColl, int idResa) throws CollegueNonTrouveException {
		Collegue col = this.collegueRepo.findById(idColl).orElseThrow(() -> new CollegueNonTrouveException(""));
		ReservationsCovoiturage resa = this.reservationsCovoiturageRepo.findById(idResa)
				.orElseThrow(() -> new CollegueNonTrouveException(""));
		resa.getListePassagers().add(col);
		this.reservationsCovoiturageRepo.ajouterPassager(resa, idResa);

	}

}
