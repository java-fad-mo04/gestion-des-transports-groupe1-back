package dev.domain;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Collegue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;

	private String prenom;

	private String email;

	private String motDePasse;

	@OneToMany(mappedBy = "collegue", cascade = CascadeType.PERSIST)
	private List<RoleCollegue> roles;

	private Long telephone;

	private String permis;

	private String urlPhoto;

	
	@OneToMany(mappedBy = "collegue")
	private Set<ReservationsSociete> reservations = new HashSet<ReservationsSociete>();

	@OneToMany(mappedBy = "collegue")
	private Set<ReservationsCovoiturage> reservationsCovoit = new HashSet<ReservationsCovoiturage>();
	
	
	public Collegue() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<RoleCollegue> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleCollegue> roles) {
		this.roles = roles;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public Set<ReservationsSociete> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationsSociete> reservations) {
		this.reservations = reservations;
	}

	public Set<ReservationsCovoiturage> getReservationsCovoit() {
		return reservationsCovoit;
	}

	public void setReservationsCovoit(Set<ReservationsCovoiturage> reservationsCovoit) {
		this.reservationsCovoit = reservationsCovoit;
	}

}
