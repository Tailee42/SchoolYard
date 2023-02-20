package fr.isika.cda.spring.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.cda.entities.school.Membership;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long>{

}
