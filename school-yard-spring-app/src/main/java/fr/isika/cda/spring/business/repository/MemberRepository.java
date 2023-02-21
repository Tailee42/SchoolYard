package fr.isika.cda.spring.business.repository;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
	
	@Query("SELECT m FROM Member m WHERE m.user.id = :userId")
    List<Member> findByUserId(@Param("userId") Long userId);
	
	@Query("SELECT s FROM Student s WHERE s.id = :memberId")
	List<Student> findStudentsByMemberId(@Param("memberId") Long memberId);
	
	@Query("SELECT t FROM Teacher t WHERE t.id = :memberId")
	List<Teacher> findTeachersByMemberId(@Param("memberId") Long memberId);
	
	@Query("SELECT a FROM Admin a WHERE a.id = :memberId")
	List<Admin> findAdminsByMemberId(@Param("memberId") Long memberId);
}
