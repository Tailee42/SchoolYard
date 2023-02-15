package fr.isika.cda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Feature {
	
	@Id
	@GeneratedValue
	private Long id;
	private String featureTitle;
	private String featureDescription;
	
	public String getFeatureTitle() {
		return featureTitle;
	}
	public void setFeatureTitle(String featureTitle) {
		this.featureTitle = featureTitle;
	}
	public String getFeatureDescription() {
		return featureDescription;
	}
	public void setFeatureDescription(String featureDescription) {
		this.featureDescription = featureDescription;
	}
	public Long getId() {
		return id;
	}

	
	
	
}
