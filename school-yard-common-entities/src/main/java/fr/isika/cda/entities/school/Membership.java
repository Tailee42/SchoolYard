package fr.isika.cda.entities.school;

import fr.isika.cda.entities.subscription.Subscription;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	private LocalDateTime endingDate;
	private LocalDateTime startingDate;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Payment payment;
	
	@OneToOne
	private Subscription subscription;
	
	public Membership() {
	}

	public Membership(LocalDateTime endingDate, LocalDateTime startingDate, Subscription subscription) {
		this.endingDate = endingDate;
		this.startingDate = startingDate;
		this.subscription = subscription;
	}

	public LocalDateTime getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDateTime endingDate) {
		this.endingDate = endingDate;
	}

	public LocalDateTime getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDateTime startingDate) {
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
