package dev.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)

public class Reservations {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	private String depart;
	
	private String destination;
	
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name="ID_COLLEGUE")
	private Collegue collegue;
	
	
	public Reservations() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	
	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}
	
}
