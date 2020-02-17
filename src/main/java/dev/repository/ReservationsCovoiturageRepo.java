package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.ReservationsCovoiturage;


public interface ReservationsCovoiturageRepo extends JpaRepository<ReservationsCovoiturage, Integer> {

}
