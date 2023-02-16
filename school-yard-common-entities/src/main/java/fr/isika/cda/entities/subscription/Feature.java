package fr.isika.cda.entities.subscription;

import java.util.Objects;

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

	public Feature() {
		// TODO Auto-generated constructor stub
	}

	public Feature(String featureTitle, String featureDescription) {
		super();
		this.featureTitle = featureTitle;
		this.featureDescription = featureDescription;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		return Objects.equals(id, other.id);
	}

}
