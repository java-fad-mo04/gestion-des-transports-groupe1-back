package dev.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.domain.ReservationsCovoiturage;

public interface ReservationsCovoiturageRepo extends JpaRepository<ReservationsCovoiturage, Integer> {

	@Query("update ReservationsCovoiturage r set r= :resa where r.id = :id")
	@Modifying
	@Transactional
	void ajouterPassager(@Param("resa") ReservationsCovoiturage resa, @Param("id") int id);
}
