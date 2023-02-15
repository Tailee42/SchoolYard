package fr.isika.cda.spring.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.entities.Feature;
import fr.isika.cda.spring.business.repository.FeatureRepository;

@Service
public class FeatureService {
	
	@Autowired
	private FeatureRepository featureRepository;
	
	public void createFeature(String title, String description) {
		Feature feature = new Feature();
		feature.setFeatureTitle(title);
		feature.setFeatureDescription(description);
		featureRepository.save(feature);
	}
	
	public void updateFeature(Feature feature) {
		featureRepository.save(feature);
	}
	
	public void deleteFeature(Feature feature) {
		featureRepository.delete(feature);
	}
	
	public List<Feature> findAll(){
		return (List<Feature>) featureRepository.findAll(); 
	}
	
	public Feature findById(Long id) {
		Optional<Feature> feature = featureRepository.findById(id);
		if (feature.isPresent()) {
			return feature.get();
		} else {
			return null;
		}
	}

}
