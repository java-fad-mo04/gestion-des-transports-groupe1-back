package dev.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.repository.VehiculeSocieteRepo;
import dev.controller.vm.VehiculeSocieteVM;

/**
 * Classe de service pour les méthodes utilisés par la classe
 * VehiculesSocieteController
 * - lister les véhicules de société
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
	
}
