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
}
