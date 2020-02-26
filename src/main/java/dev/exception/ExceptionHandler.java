package dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { CollegueNonTrouveException.class })
	public ResponseEntity<String> reservationPresent(CollegueNonTrouveException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("collègue non trouvé");
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = { VehiculeNonTrouveException.class })
	public ResponseEntity<String> vehiculeNonPresent(VehiculeNonTrouveException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Véhicule non trouvé");
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = { VehiculeTrouveException.class })
	public ResponseEntity<String> VehiculePresent(VehiculeTrouveException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Véhicule de société déjà existant");
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = { FormErrorException.class })
	public ResponseEntity<String> FormError(FormErrorException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
