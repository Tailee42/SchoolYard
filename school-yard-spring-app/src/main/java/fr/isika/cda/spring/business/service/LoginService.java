package fr.isika.cda.spring.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.entities.users.SuperAdmin;
import fr.isika.cda.spring.business.repository.SuperAdminRepository;
import fr.isika.cda.utils.EncodingUtil;

@Service
public class LoginService {

	@Autowired
	private SuperAdminRepository superAdminRepository;

	public Optional<SuperAdmin> findByLogin(String login) {
		return superAdminRepository.findByLogin(login);
	}

	public boolean isSecured(String login, String password) {
		Optional<SuperAdmin> optional = findByLogin(login);
		if(optional.isPresent()) {
			SuperAdmin superAdmin = optional.get();
			return validatePassword(superAdmin, password);
		}
		return false;
	}
	
	private boolean validatePassword(SuperAdmin superAdmin, String password) {
		String pwd = EncodingUtil.encode(password);
		return superAdmin.getPassword().equals(pwd);
	}

}
