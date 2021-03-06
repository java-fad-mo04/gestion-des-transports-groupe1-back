package dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.dto.VehiculesSocieteDTO;
import dev.controller.dto.VehiculesSocieteFiltreDTO;
import dev.controller.vm.ReservationsSocieteVM;
import dev.controller.vm.VehiculeSocieteVM;
import dev.exception.VehiculeNonTrouveException;
import dev.exception.VehiculeTrouveException;
import dev.service.VehiculesSocieteService;

/***
 * controller get et post pour les vehicules de societe
 * - lister les vehicules de societe
 * - filtrer les vehicules de societe par immatriculation et marque
 * - creer un vehicule
 * - mettre a jour un vehicule
 * - supprimer un vehicule
 * 
 * @author audrey
 *
 */

@RestController
@RequestMapping(value = "vehiculesSociete")
public class VehiculesSocieteController {

	private VehiculesSocieteService vehiculesSocieteService;

	
	public VehiculesSocieteController(VehiculesSocieteService vehiculesSocieteService) {
		super();
		this.vehiculesSocieteService = vehiculesSocieteService;
	}
	
	/**
	 * GET vehiculesSociete : méthode qui récupere les véhicules de société 
	 * 
	 * @return liste de vehicules de societe (VehiculeSocieteVM)
	 */
	@GetMapping()
	public List<VehiculeSocieteVM> listerVehiculesSociete() {
		return this.vehiculesSocieteService.listerVehiculesSociete();
	}
	
	/**
	 * POST /vehiculesSociete
	 * 
	 * @param vehiculeDTO : données json
	
	 * @return liste des vehicules filtrés
	 */
	@PostMapping
	public List<VehiculeSocieteVM> chercherVehiculeAvecFiltre(
			@RequestBody VehiculesSocieteFiltreDTO vehiculeDTO) {
		return this.vehiculesSocieteService.chercherVehiculeAvecFiltre(vehiculeDTO);
	}
	
	/**
	 * POST /vehiculesSociete/creer
	 * 
	 * @param vehiculeDTO : données json
	
	 * @return texte si véhicule a bien été ajouté
	 */
	@PostMapping(value="/creer")
	public ResponseEntity<?> creerVehiculeSociete(@RequestBody @Valid VehiculesSocieteDTO vehiculePost) throws VehiculeTrouveException {
		
		this.vehiculesSocieteService.creerVehiculeSociete(vehiculePost);
		return ResponseEntity.status(HttpStatus.CREATED).body("Véhicule ajouté en base de données");
	}
	
	/**
	 * PATCH /vehiculesSociete/editer/idVehicule
	 * 
	 * @param vehiculeDTO : données json
	 * @param idVehicule : type Long
	
	 * @return texte si véhicule modifié
	 */
	@PatchMapping(value="/editer/{idVehicule}")
	public ResponseEntity<?> updaterVehiculeSociete(@PathVariable Long idVehicule,
			@RequestBody VehiculesSocieteDTO vehiculePost) throws VehiculeTrouveException,VehiculeNonTrouveException {

		this.vehiculesSocieteService.updaterVehiculeSociete(idVehicule, vehiculePost);
		return ResponseEntity.status(HttpStatus.OK).body("Véhicule modifié");
	}
	
	/**
	 * DELETE /vehiculesSociete/supprimer/idVehicule
	 * 
	 * @param idVehicule : type Long
	
	 * @return texte si vehicule supprimé
	 */
	@DeleteMapping(value="/supprimer/{idVehicule}")
	public ResponseEntity<?> supprimerVehiculeSociete(@PathVariable Long idVehicule) throws VehiculeNonTrouveException {
		
		this.vehiculesSocieteService.supprimerVehiculeSociete(idVehicule);
		return ResponseEntity.status(HttpStatus.CREATED).body("Véhicule supprimé en base de données");
	}
	
	@GetMapping(value="/afficher/{immatriculation}")
	public VehiculeSocieteVM afficherDetailsVehiculesSociete(@PathVariable String immatriculation) throws VehiculeNonTrouveException {
		return this.vehiculesSocieteService.afficherDetailsVehiculesSociete(immatriculation);
	}
}
