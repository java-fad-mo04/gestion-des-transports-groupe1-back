package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import dev.repository.VehiculeSocieteRepo;
import dev.controller.dto.VehiculesSocieteDTO;
import dev.controller.dto.VehiculesSocieteFiltreDTO;
import dev.controller.vm.VehiculeSocieteVM;
import dev.domain.VehiculeSociete;

/**
 * Classe de service pour les méthodes utilisés par la classe
 * VehiculesSocieteController
 * - lister les véhicules de société
 * - filtrer les vehicules de societe par immatriculation ou par marque
 * - créer un véhicule de société
 */

@Service
public class VehiculesSocieteService {

	private VehiculeSocieteRepo vehiculesSocieteRepo;

	public VehiculesSocieteService(VehiculeSocieteRepo vehiculesSocieteRepo) {
		super();
		this.vehiculesSocieteRepo = vehiculesSocieteRepo;
	}
	
	public List<VehiculeSocieteVM> listerVehiculesSociete(){

		return this.vehiculesSocieteRepo.findAll().stream().map(VehiculeSocieteVM::new)
				.collect(Collectors.toList());
	}
	
	public List<VehiculeSocieteVM> chercherVehiculeAvecFiltre(VehiculesSocieteFiltreDTO vehiculeFiltrePost) {
		return this.vehiculesSocieteRepo.findByImmatriculationOrMarque(vehiculeFiltrePost.getImmatriculation(), vehiculeFiltrePost.getMarque())
				.stream().map(VehiculeSocieteVM::new)
				.collect(Collectors.toList());
	}
	
	public void creerVehiculeSociete (VehiculesSocieteDTO vehiculeDTOPost) throws EntityExistsException {
		
		if(this.vehiculesSocieteRepo.findByImmatriculationExist(vehiculeDTOPost.getImmatriculation())){
			throw new EntityExistsException();
		}
		
		VehiculeSociete vehiculeNewPost = new VehiculeSociete();
		
		vehiculeNewPost.setImmatriculation(vehiculeDTOPost.getImmatriculation());
		vehiculeNewPost.setMarque(vehiculeDTOPost.getMarque());
		vehiculeNewPost.setModele(vehiculeDTOPost.getModele());
		vehiculeNewPost.setCategorie(vehiculeDTOPost.getCategorie());
		vehiculeNewPost.setStatut(vehiculeDTOPost.getStatut());
		vehiculeNewPost.setUrlPhoto(vehiculeDTOPost.getUrlPhoto());
		
		this.vehiculesSocieteRepo.save(vehiculeNewPost);
	
	}
}
