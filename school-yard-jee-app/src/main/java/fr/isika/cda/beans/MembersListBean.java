package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class MembersListBean {

	@Inject
	private MemberRepository memberRepository;

	private List<Member> members;
	private String memberRole;

	public List<Member> allMembersForOneUser() {
		members = memberRepository.getAllMembersForOneUser(SessionUtils.getConnectedUser().getId());
		return members;
	}

	public String goToIndexSchool(Member member) {
		SessionUtils.setConnectedMember(member);
		SessionUtils.setCurrentSchool(member.getSchool());
		return "indexSchool";
	}

	public void getMemberRole(Member member) {
		if (member instanceof Admin) {
			memberRole = "Administrateur";
		} else if (member instanceof Teacher) {
			memberRole = "Professeur";
		} else if (member instanceof Student) {
			memberRole = "Elève";
		} else {
			memberRole = "Utilisateur sans aucun rôle";
		}
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

}
