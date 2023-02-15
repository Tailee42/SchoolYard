package fr.isika.cda.spring.business.repository;

import fr.isika.cda.entities.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
}
