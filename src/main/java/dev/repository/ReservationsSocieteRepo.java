package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.ReservationsSociete;

public interface ReservationsSocieteRepo  extends JpaRepository<ReservationsSociete, Integer>  {

}
