package fr.isika.cda.entities.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subscription {

	@Id
	@GeneratedValue
	private Long id;
	private Double price;
	private int duration;
	private String name;

	@ManyToMany
	private List<Feature> features = new ArrayList<>();

	public Subscription() {
	}

	public Subscription(Double price, int duration, String name, Feature feature) {
		super();
		this.price = price;
		this.duration = duration;
		this.name = name;
		this.features.add(feature);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
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
