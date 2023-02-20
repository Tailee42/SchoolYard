package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class MembersListBean {

	@Inject
	private MemberRepository memberRepository;

	private List<Member> members = new ArrayList<Member>();

//	private Member member = new member();
//	private List<Members> member = new ArrayList<Member>();

	public List<Member> allMembersForOneUser() {
		members = memberRepository.getAllMembersForOneUser();
		return members;
	}
	
	public void displayUserconnected() {
    	User userConnected = SessionUtils.getConnectedUser();
    	
    	System.out.println("L'Id de l'utilisateur connecté est : " + userConnected.getId() );
    }
	

}
