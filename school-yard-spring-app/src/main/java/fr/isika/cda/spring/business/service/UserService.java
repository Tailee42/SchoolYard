package fr.isika.cda.spring.business.service;

import java.util.List;
import java.util.Optional;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cda.spring.business.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public List<User> findAllByStatus(UserStatus status){
		return userRepository.findAllByStatus(status);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}

	
}
