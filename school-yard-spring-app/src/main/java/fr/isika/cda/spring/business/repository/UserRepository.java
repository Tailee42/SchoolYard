package fr.isika.cda.spring.business.repository;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserStatus;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.status = :userStatus")
    List<User> findAllByStatus(@Param("userStatus") UserStatus userStatus);
	
}
