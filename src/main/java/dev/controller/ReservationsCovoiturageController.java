package dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.vm.ReservationCovoiturageVM;
import dev.service.ReservationCovoiturageService;

@RestController
@RequestMapping("reservationsCovoiturage")
public class ReservationsCovoiturageController {

	private ReservationCovoiturageService covoiturageService;

	/**
	 * @param covoiturageService
	 */
	public ReservationsCovoiturageController(ReservationCovoiturageService covoiturageService) {
		super();
		this.covoiturageService = covoiturageService;
	}

	@GetMapping
	public List<ReservationCovoiturageVM> listerCovoiturage() {
		return this.covoiturageService.listerCovoiturages();

	}

}
