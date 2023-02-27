package fr.isika.cda.entities.school;

import fr.isika.cda.entities.subscription.Subscription;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Membership implements Serializable   {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2673403054115715650L;
	
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate endingDate;
	private LocalDate startingDate;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Payment payment;
	
	@OneToOne
	private Subscription subscription;
	
	public Membership() {
	}

	public Membership(LocalDate endingDate, LocalDate startingDate, Subscription subscription) {
		this.endingDate = endingDate;
		this.startingDate = startingDate;
		this.subscription = subscription;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Long getId() {
		return id;
	}

	
	
}
