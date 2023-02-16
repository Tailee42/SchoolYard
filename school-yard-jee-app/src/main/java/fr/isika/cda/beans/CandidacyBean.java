package fr.isika.cda.beans;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.UserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class CandidacyBean {

	@Inject
	private SchoolRepository sr;
	
	@Inject
	private UserRepository ur;
	
	public void apply(Long schoolId) {
		
		if(SessionUtils.isUserConnected()) {
			User userConnected = SessionUtils.getConnectedUser();
			Member member = ur.getMemberyUserId(userConnected.getId());
			Optional<School> schoolSelected = sr.getSchoolById(schoolId);
			if(schoolSelected.isPresent()) {
				School school = schoolSelected.get();
				school.addMember(member);
				sr.update(school);
			}
		}
	}
	
}
