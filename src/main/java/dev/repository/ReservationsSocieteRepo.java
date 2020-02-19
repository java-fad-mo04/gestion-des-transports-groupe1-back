package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.Collegue;
import dev.domain.ReservationsSociete;

public interface ReservationsSocieteRepo  extends JpaRepository<ReservationsSociete, Long>  {

	List<ReservationsSociete> findByCollegue(Collegue col);
}
