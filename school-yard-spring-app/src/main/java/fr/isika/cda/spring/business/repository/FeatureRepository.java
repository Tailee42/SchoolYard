package fr.isika.cda.spring.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.cda.entities.Feature;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long>{

}
