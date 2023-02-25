package fr.isika.cda.spring.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.cda.entities.users.SuperAdmin;

@Repository
public interface SuperAdminRepository extends CrudRepository<SuperAdmin, Long> {

	@Query("SELECT sa FROM SuperAdmin sa WHERE sa.login = :login")
	Optional<SuperAdmin> findByLogin(String login);

}
