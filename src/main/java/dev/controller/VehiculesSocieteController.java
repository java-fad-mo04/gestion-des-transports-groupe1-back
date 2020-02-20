package dev.controller;

import java.util.List;

/***
 * controller get et post pour les vehicules de societe
 * @author audrey
 *
 */

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.VehiculeSocieteVM;
import dev.service.VehiculesSocieteService;


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
	
}
