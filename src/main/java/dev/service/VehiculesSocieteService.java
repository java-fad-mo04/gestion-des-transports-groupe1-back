package dev.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.VehiculesSocieteDTO;
import dev.controller.dto.VehiculesSocieteFiltreDTO;
import dev.controller.vm.VehiculeSocieteVM;
import dev.domain.ReservationsSociete;
import dev.domain.Statut;
import dev.domain.VehiculeSociete;
import dev.exception.VehiculeNonTrouveException;
import dev.exception.VehiculeTrouveException;
import dev.repository.ReservationsSocieteRepo;
import dev.repository.VehiculeSocieteRepo;
import dev.utils.ProtocoleMail;

/**
 * Classe de service pour les méthodes utilisés par la classe
 * VehiculesSocieteController - lister les véhicules de société - filtrer les
 * vehicules de societe par immatriculation ou par marque - créer un véhicule de
 * société - mettre a jour un vehicule de société - supprimer un véhicule de
 * société - afficher un vehicule de societe choisi
 */

@Service
public class VehiculesSocieteService {

	private VehiculeSocieteRepo vehiculesSocieteRepo;
	private ReservationsSocieteRepo reservationsSocieteRepo;

	/**
	 * @param vehiculesSocieteRepo
	 * @param reservationsSocieteRepo
	 */
	public VehiculesSocieteService(VehiculeSocieteRepo vehiculesSocieteRepo,
			ReservationsSocieteRepo reservationsSocieteRepo) {
		super();
		this.vehiculesSocieteRepo = vehiculesSocieteRepo;
		this.reservationsSocieteRepo = reservationsSocieteRepo;
	}

	public List<VehiculeSocieteVM> listerVehiculesSociete() {

		return this.vehiculesSocieteRepo.findAll().stream().map(VehiculeSocieteVM::new).collect(Collectors.toList());
	}

	public List<VehiculeSocieteVM> chercherVehiculeAvecFiltre(VehiculesSocieteFiltreDTO vehiculeFiltrePost) {
		return this.vehiculesSocieteRepo
				.findByImmatriculationOrMarque(vehiculeFiltrePost.getImmatriculation(), vehiculeFiltrePost.getMarque())
				.stream().map(VehiculeSocieteVM::new).collect(Collectors.toList());
	}

	public void creerVehiculeSociete(VehiculesSocieteDTO vehiculeDTOPost) throws VehiculeTrouveException {

		if (this.vehiculesSocieteRepo.findByImmatriculationExist(vehiculeDTOPost.getImmatriculation())) {
			throw new VehiculeTrouveException("");
		}

		VehiculeSociete vehiculeNewPost = new VehiculeSociete();

		vehiculeNewPost.setImmatriculation(vehiculeDTOPost.getImmatriculation());
		vehiculeNewPost.setMarque(vehiculeDTOPost.getMarque());
		vehiculeNewPost.setModele(vehiculeDTOPost.getModele());
		vehiculeNewPost.setCategorie(vehiculeDTOPost.getCategorie());

		if (vehiculeDTOPost.getStatut() != null) {
			vehiculeNewPost.setStatut(vehiculeDTOPost.getStatut());
		} else {
			vehiculeNewPost.setStatut(Statut.EN_SERVICE);
		}
		vehiculeNewPost.setUrlPhoto(vehiculeDTOPost.getUrlPhoto());

		this.vehiculesSocieteRepo.save(vehiculeNewPost);

	}

	public void updaterVehiculeSociete(Long idVehicule, VehiculesSocieteDTO vehiculeDTOPost)
			throws VehiculeTrouveException, VehiculeNonTrouveException {

		VehiculeSociete vehiculeEdit = this.vehiculesSocieteRepo.findById(idVehicule)
				.orElseThrow(() -> new VehiculeNonTrouveException(""));


		if (vehiculeDTOPost.getImmatriculation() != null) {
			if (!vehiculeEdit.getImmatriculation().equals(vehiculeDTOPost.getImmatriculation())) {
				if (this.vehiculesSocieteRepo.findByImmatriculationExist(vehiculeDTOPost.getImmatriculation())) {
					throw new VehiculeTrouveException("");
				}
			}
			vehiculeEdit.setImmatriculation(vehiculeDTOPost.getImmatriculation());
		}

		if (vehiculeDTOPost.getMarque() != null) {
			vehiculeEdit.setMarque(vehiculeDTOPost.getMarque());
		}

		if (vehiculeDTOPost.getModele() != null) {
			vehiculeEdit.setModele(vehiculeDTOPost.getModele());
		}

		if (vehiculeDTOPost.getCategorie() != null) {
			vehiculeEdit.setCategorie(vehiculeDTOPost.getCategorie());
		}

		if (vehiculeDTOPost.getStatut() != null) {
			vehiculeEdit.setStatut(vehiculeDTOPost.getStatut());

			if (!vehiculeDTOPost.getStatut().equals(Statut.EN_SERVICE)) {

				List<ReservationsSociete> listReservationsSociete = this.reservationsSocieteRepo
						.findReservationsByDateVehicules(vehiculeEdit);

				listReservationsSociete.forEach(resa -> {
					// envoi mail pour annuler les reservations en cours
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
					String messageEmail = "Votre réservation prévu le " + resa.getDate().format(formatter)
							+ " a été annulée.<br/>Le véhicule réservé n'est plus disponible à cette période.";

					ProtocoleMail.envoyerMailSMTP("Réservation annulée", resa.getCollegue().getEmail(), messageEmail);

					// supprimer les reservations prévues avec ce véhicule
					this.reservationsSocieteRepo.delete(resa);
				});
			}
		}

		if (vehiculeDTOPost.getUrlPhoto() != null) {
			vehiculeEdit.setUrlPhoto(vehiculeDTOPost.getUrlPhoto());
		}
		
		this.vehiculesSocieteRepo.save(vehiculeEdit);
	}

	public void supprimerVehiculeSociete(Long idVehicule) throws VehiculeNonTrouveException {

		VehiculeSociete vehicule = this.vehiculesSocieteRepo.findById(idVehicule)
				.orElseThrow(() -> new VehiculeNonTrouveException(""));

		List<ReservationsSociete> listReservationsSociete = this.reservationsSocieteRepo
				.findReservationsByDateVehicules(vehicule);

		listReservationsSociete.forEach(resa -> {
			// envoi mail pour annuler les reservations en cours
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
			String messageEmail = "Votre réservation prévu le " + resa.getDate().format(formatter)
					+ " a été annulée.<br/>Le véhicule réservé n'est plus disponible à cette période.";
			ProtocoleMail.envoyerMailSMTP("Réservation annulée", resa.getCollegue().getEmail(), messageEmail);

			// supprimer les reservations prévues avec ce véhicule
			this.reservationsSocieteRepo.delete(resa);
		});

		this.vehiculesSocieteRepo.deleteById(idVehicule);
	}

	public VehiculeSocieteVM afficherDetailsVehiculesSociete(String immatriculation) throws VehiculeNonTrouveException {

		VehiculeSociete vehicule = this.vehiculesSocieteRepo.findByImmatriculation(immatriculation)
				.orElseThrow(() -> new VehiculeNonTrouveException(""));
		// VehiculeSocieteVM vehVM = new VehiculeSocieteVM(vehicule);
		return new VehiculeSocieteVM(vehicule);
	}
}
