package fr.isika.cda.spring.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.entities.school.School;
import fr.isika.cda.spring.business.repository.SchoolRepository;

@Service
public class SchoolService {
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	public List<School> findAll(){
		return (List<School>) schoolRepository.findAll();
	}


}
