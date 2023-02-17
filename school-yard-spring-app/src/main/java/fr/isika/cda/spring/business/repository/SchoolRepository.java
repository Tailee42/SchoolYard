package fr.isika.cda.spring.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.cda.entities.school.School;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long>{

}
