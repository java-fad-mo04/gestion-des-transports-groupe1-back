package dev.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.controller.dto.ReservationsCovoiturageDTO;
import dev.controller.vm.ReservationCovoiturageVM;
import dev.exception.CollegueNonTrouveException;
import dev.service.ReservationsCovoiturageService;

/**
 * @author bapti
 *
 */
@RestController
@RequestMapping("reservationsCovoiturage")
public class ReservationsCovoiturageController {

	private ReservationsCovoiturageService covoiturageService;

	/**
	 * @param covoiturageService
	 */
	public ReservationsCovoiturageController(ReservationsCovoiturageService covoiturageService) {
		super();
		this.covoiturageService = covoiturageService;
	}

	/**
	 * GET /reservationsCovoiturage?idcol= . Cette méthode récupère toutes les
	 * annonces de covoiturage en database
	 * 
	 * @return liste des covoiturages
	 */
	@GetMapping(params = "idCol")
	public List<ReservationCovoiturageVM> listerCovoituragePubliés(@RequestParam("idCol") long idCol) {
		return this.covoiturageService.listerCovoiturages(idCol);

	}

	/**
	 * GET /reservationsCovoiturage?idPass=
	 * 
	 * @param id
	 * @return liste des covoiturages reservés par le collegue
	 */
	@GetMapping(params = "idPass")
	public List<ReservationCovoiturageVM> listerCovoiturageReservés(@RequestParam("idPass") long id) {
		return this.covoiturageService.listerCovoiturageReservés(id);
	}

	/**
	 * POST /reservationsCovoiturage
	 * 
	 * @param resaDTO,
	 *            recupéré au format JSON sert de filtre sur la liste des
	 *            coviturages
	 * @return liste des covoiturages filtrés
	 */
	@PostMapping
	public List<ReservationCovoiturageVM> chercherCovoiturageAvecFiltre(
			@RequestBody ReservationsCovoiturageDTO resaDTO) {
		return this.covoiturageService.chercherCovoiturageAvecFiltre(resaDTO);
	}

	@PatchMapping(path = "{idCol}", params = "idResa")
	public ResponseEntity<?> ajouterPassager(@RequestParam("idResa") int idResa, @PathVariable long idCol)
			throws CollegueNonTrouveException {
		this.covoiturageService.ajouterPassager(idCol, idResa);
		return ResponseEntity.status(HttpStatus.CREATED).body("passager ajouté en database");

	}
}
