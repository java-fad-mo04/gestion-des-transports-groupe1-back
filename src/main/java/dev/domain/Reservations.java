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
	
	private Collegue collegue;
	
	@ManyToOne
	@JoinColumn(name="ID_COLLEGUE")
	private Collegue reservations;
	
}
