package fr.isika.cda.spring.business;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fr.isika.cda.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();

}
