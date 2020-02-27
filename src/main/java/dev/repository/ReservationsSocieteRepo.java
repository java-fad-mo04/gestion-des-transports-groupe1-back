package dev.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.domain.Collegue;
import dev.domain.ReservationsSociete;
import dev.domain.VehiculeSociete;

/***
 * requete sql table reservationsSociete
 * requetes personnalisées:
 * - lister les reservations selon profil connecté
 * - verifier si le profil connecté n a pas de reservations de vehicules de societe lors de la creation d une reservation
 * @author audrey
 *
 */
public interface ReservationsSocieteRepo  extends JpaRepository<ReservationsSociete, Long>  {

	List<ReservationsSociete> findByCollegue(Collegue col);
	
	@Query("select case when (count(*) >0) then true else false end from ReservationsSociete rs where date <= :dateRetour and dateRetour >= :dateDepart  and collegue = (:col)")
	Boolean findByDateDepartAndDateRetourByCollegue(@Param("dateDepart") LocalDateTime dateDepart, @Param("dateRetour") LocalDateTime dateRetour, @Param("col") Collegue col);

	@Query("select rs from ReservationsSociete rs inner join rs.vehicules v where (rs.date >= DATE(NOW()) or rs.dateRetour >=  DATE(NOW())) and v = :idVehicule")
	List<ReservationsSociete> findReservationsByDateVehicules(@Param("idVehicule") VehiculeSociete idVehicule);
	
	
	List<ReservationsSociete> findReservationsByVehicules(VehiculeSociete idVehicule);
}
