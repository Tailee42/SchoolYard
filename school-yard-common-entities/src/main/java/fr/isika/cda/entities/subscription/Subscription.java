package fr.isika.cda.entities.subscription;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subscription {

	@Id
	@GeneratedValue
	private Long id;
	private Double price;
	private Long duration;
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Feature> features = new ArrayList<>();

	public Subscription() {
	}

	public Subscription(Double price, Long duration, String name) {
		super();
		this.price = price;
		this.duration = duration;
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getDuration() {
		return duration;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
