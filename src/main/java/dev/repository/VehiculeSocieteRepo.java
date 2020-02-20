package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.domain.VehiculeSociete;

public interface VehiculeSocieteRepo extends JpaRepository<VehiculeSociete, Long> {

	List<VehiculeSociete> findByImmatriculationOrMarque(String immatriculation, String marque);
	
	@Query("select case when (count(*) >0) then true else false end from VehiculeSociete vs where immatriculation = (:immatriculation)")
	Boolean findByImmatriculationExist(@Param("immatriculation") String immatriculation);
}
