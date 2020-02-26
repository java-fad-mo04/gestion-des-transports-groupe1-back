package dev.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.controller.dto.ReservationsCovoiturageDTO;
import dev.controller.vm.ReservationCovoiturageVM;
import dev.domain.Collegue;
import dev.domain.ReservationsCovoiturage;
import dev.domain.VehiculePerso;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepo;
import dev.repository.ReservationsCovoiturageRepo;
import dev.repository.VehiculePersoRepo;
import dev.utils.ProtocoleMail;

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
	 * instancie le repository vehiculePerso
	 */
	private VehiculePersoRepo veRepo;

	/**
	 * @param reservationsCovoiturageRepo
	 * @param collegueRepo
	 * @param veRepo
	 */
	public ReservationsCovoiturageService(ReservationsCovoiturageRepo reservationsCovoiturageRepo,
			CollegueRepo collegueRepo, VehiculePersoRepo veRepo) {
		super();
		this.reservationsCovoiturageRepo = reservationsCovoiturageRepo;
		this.collegueRepo = collegueRepo;
		this.veRepo = veRepo;
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
	public List<ReservationCovoiturageVM> listerCovoiturageReserves(long id) {
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

	/**
	 * creation d'une annonce de covoiturage
	 * 
	 * @param idCol
	 * @param resaDTO
	 * @throws CollegueNonTrouveException
	 */
	public void creerAnnonce(long idCol, ReservationsCovoiturageDTO resaDTO) throws CollegueNonTrouveException {
		Collegue col = this.collegueRepo.findById(idCol).orElseThrow(() -> new CollegueNonTrouveException(""));

		VehiculePerso veP = new VehiculePerso();
		veP.setImmatriculation(resaDTO.getVehicule().getImmatriculation());
		veP.setMarque(resaDTO.getVehicule().getMarque());
		veP.setModele(resaDTO.getVehicule().getModele());
		veP.setNombrePlace(resaDTO.getVehicule().getNombrePlace());
		this.veRepo.save(veP);

		ReservationsCovoiturage resa = new ReservationsCovoiturage();
		resa.setCollegue(col);
		resa.setDate(resaDTO.getDate());
		resa.setDepart(resaDTO.getDepart());
		resa.setDestination(resaDTO.getDestination());
		resa.setVehicules(veP);
		this.reservationsCovoiturageRepo.save(resa);

	}

	/**
	 * suppression d'une resa de covoiturage
	 * 
	 * @param idCol
	 * @param idResa
	 * @throws CollegueNonTrouveException
	 */
	public void supprimerReservation(long idCol, int idResa) throws CollegueNonTrouveException {
		Collegue col = this.collegueRepo.findById(idCol).orElseThrow(() -> new CollegueNonTrouveException(""));
		ReservationsCovoiturage resa = this.reservationsCovoiturageRepo.findById(idResa)
				.orElseThrow(() -> new CollegueNonTrouveException(""));
		resa.getListePassagers().removeIf(p -> p.getId() == col.getId());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
		String messageEmail1 = " le passager " + col.getPrenom() + " " + col.getNom().toUpperCase()
				+ " a annulé sa reservation pour le coivoiturage prévu le " + resa.getDate().format(formatter)
				+ " au départ de " + resa.getDepart() + " et à destination de " + resa.getDestination();
		String messageEmail2 = "Votre réservation de covoiturage prévue le " + resa.getDate().format(formatter)
				+ " au départ de " + resa.getDepart() + " et à destination de " + resa.getDestination()
				+ " a bien été annulée";
		ProtocoleMail.envoyerMailSMTP("Reservation Covoiturage annulé", resa.getCollegue().getEmail(), messageEmail1);
		ProtocoleMail.envoyerMailSMTP("Reservation Covoiturage annulé", col.getEmail(), messageEmail2);
		this.reservationsCovoiturageRepo.ajouterPassager(resa, idResa);
	}

	/**
	 * supression d'une annonce de coivoiturage
	 * 
	 * @param idResa
	 */
	public void supprimerAnnonceCvoiturage(int idResa) {
		
		this.reservationsCovoiturageRepo.findById(idResa)
				.orElseThrow(() -> new EntityNotFoundException("reservation non trouvée")).getListePassagers()
				.forEach(col -> {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
					String messageEmail = "Votre covoiturage prévu le "
							+ this.reservationsCovoiturageRepo.findById(idResa).get().getDate().format(formatter)
							+ " au départ de " + this.reservationsCovoiturageRepo.findById(idResa).get().getDepart()
							+ " et à destination de "
							+ this.reservationsCovoiturageRepo.findById(idResa).get().getDestination()
							+ " a été annulée.";

					ProtocoleMail.envoyerMailSMTP("Covoiturage annulé", col.getEmail(), messageEmail);
					ProtocoleMail.envoyerMailSMTP("Covoiturage annulé",
							this.reservationsCovoiturageRepo.findById(idResa).get().getCollegue().getEmail(),
							messageEmail);
				});
		this.reservationsCovoiturageRepo.deleteById(idResa);
	}

	/**
	 * lister touts les resa de covoiturage
	 * 
	 * @return liste de resa REservationsCovoiturageVM
	 */
	public List<ReservationCovoiturageVM> listerAll() {
		return this.reservationsCovoiturageRepo.findAll().stream().map(ReservationCovoiturageVM::new)
				.collect(Collectors.toList());
	}
}
