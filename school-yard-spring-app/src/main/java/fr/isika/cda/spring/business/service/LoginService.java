package fr.isika.cda.spring.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.entities.users.SuperAdmin;
import fr.isika.cda.spring.business.repository.SuperAdminRepository;

@Service
public class LoginService {

	@Autowired
	private SuperAdminRepository superAdminRepository;

	public Optional<SuperAdmin> findByLogin(String login) {
		return superAdminRepository.findByLogin(login);
	}
}
