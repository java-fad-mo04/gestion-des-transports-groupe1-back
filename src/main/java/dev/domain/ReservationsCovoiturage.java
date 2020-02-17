package dev.domain;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class ReservationsCovoiturage extends Reservations {

	private List<Collegue> listePassagers;
	
	//private 
}
