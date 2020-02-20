package dev;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Categorie;
import dev.domain.Collegue;
import dev.domain.ReservationsCovoiturage;
import dev.domain.ReservationsSociete;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Statut;
import dev.domain.VehiculePerso;
import dev.domain.VehiculeSociete;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.ReservationsCovoiturageRepo;
import dev.repository.ReservationsSocieteRepo;
import dev.repository.VehiculePersoRepo;
import dev.repository.VehiculeSocieteRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private VehiculeSocieteRepo vehiculeSocieteRepo;
	private VehiculePersoRepo vehiculePersoRepo;
	private ReservationsSocieteRepo resaSocieteRepo;
	private ReservationsCovoiturageRepo resaCovoiturageRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, VehiculeSocieteRepo vehiculeSocieteRepo,
			VehiculePersoRepo vehiculePersoRepo, ReservationsSocieteRepo resaSocieteRepo,
			ReservationsCovoiturageRepo resaCovoiturageRepo) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.vehiculeSocieteRepo = vehiculeSocieteRepo;
		this.vehiculePersoRepo = vehiculePersoRepo;
		this.resaSocieteRepo = resaSocieteRepo;
		this.resaCovoiturageRepo = resaCovoiturageRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();
		col3.setNom("Chauffeur");
		col3.setPrenom("DEV");
		col3.setEmail("chauffeur@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_UTILISATEUR),
				new RoleCollegue(col3, Role.ROLE_CHAUFFEUR)));
		this.collegueRepo.save(col3);

		Collegue col4 = new Collegue();
		col4.setNom("Dupont");
		col4.setPrenom("Jean");
		col4.setEmail("jean.dupont@dev.fr");
		col4.setMotDePasse(passwordEncoder.encode("superpass"));
		col4.setRoles(Arrays.asList(new RoleCollegue(col4, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col4);

		VehiculeSociete ve = new VehiculeSociete();
		ve.setMarque("Peugeot");
		ve.setCategorie(Categorie.BERLINE_TAILLE_L);
		ve.setImmatriculation("AA-123-BB");
		ve.setModele("308");
		ve.setStatut(Statut.EN_SERVICE);
		ve.setUrlPhoto("https://i.ytimg.com/vi/fqNzmIkRFHk/maxresdefault.jpg");
		this.vehiculeSocieteRepo.save(ve);

		VehiculeSociete ve2 = new VehiculeSociete();
		ve2.setMarque("Honda");
		ve2.setCategorie(Categorie.BERLINE_TAILLE_S);
		ve2.setImmatriculation("CA-747-DE");
		ve2.setModele("Civic");
		ve2.setStatut(Statut.EN_SERVICE);
		ve2.setUrlPhoto("https://i.ytimg.com/vi/fqNzmIkRFHk/maxresdefault.jpg");
		this.vehiculeSocieteRepo.save(ve2);
		
		VehiculePerso veP1 = new VehiculePerso();
		veP1.setImmatriculation("CC-123-CC");
		veP1.setMarque("Renault");
		veP1.setModele("Scenic");
		veP1.setNombrePlace(4);
		this.vehiculePersoRepo.save(veP1);

		VehiculePerso veP2 = new VehiculePerso();
		veP2.setImmatriculation("DD-123-CC");
		veP2.setMarque("Ford");
		veP2.setModele("Focus");
		veP2.setNombrePlace(4);
		this.vehiculePersoRepo.save(veP2);

		ReservationsSociete rs1_chauff = new ReservationsSociete();
		rs1_chauff.setDate(LocalDateTime.of(2020, 02, 25, 10, 00));
		rs1_chauff.setDateRetour(LocalDateTime.of(2020, 02, 27, 18, 00));
		rs1_chauff.setCollegue(col2);
		rs1_chauff.setChauffeur(col3);
		rs1_chauff.setVehicules(ve);
		rs1_chauff.setAvecChauffeur(true);
		this.resaSocieteRepo.save(rs1_chauff);

		ReservationsSociete rs2 = new ReservationsSociete();
		rs2.setDate(LocalDateTime.of(2020, 03, 8, 10, 00));
		rs2.setDateRetour(LocalDateTime.of(2020, 03, 9, 17, 00));
		rs2.setCollegue(col1);
		rs2.setVehicules(ve);
		rs2.setAvecChauffeur(false);
		this.resaSocieteRepo.save(rs2);

		ReservationsCovoiturage rc1 = new ReservationsCovoiturage();
		rc1.setDepart("Lyon");
		rc1.setDestination("Marseille");
		rc1.setDate(LocalDateTime.of(2020, 03, 5, 8, 30));
		rc1.setCollegue(col1);
		rc1.setListePassagers(Arrays.asList(col1, col3));
		rc1.setVehicules(veP1);
		this.resaCovoiturageRepo.save(rc1);

		ReservationsCovoiturage rc2 = new ReservationsCovoiturage();
		rc2.setDepart("Montpellier");
		rc2.setDestination("Brest");
		rc2.setDate(LocalDateTime.of(2020, 04, 5, 8, 30));
		rc2.setCollegue(col2);
		rc2.setListePassagers(Arrays.asList(col1, col3));
		rc2.setVehicules(veP2);
		this.resaCovoiturageRepo.save(rc2);

	}

}
