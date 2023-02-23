package fr.isika.cda.spring.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.entities.school.Membership;
import fr.isika.cda.spring.business.repository.MembershipRepository;

@Service
public class MembershipService {
	
	@Autowired
	private MembershipRepository membershipRepository;

	public List<Membership> findAll() {
		return (List<Membership>) membershipRepository.findAll();
	}

	public void save(Membership membership) {
		membershipRepository.save(membership);
		
	}

}
