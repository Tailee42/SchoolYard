package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SynchronousLessonRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class MembersListBean {

	@Inject
	private MemberRepository memberRepository;
	@Inject
	private SynchronousLessonRepository synchronousLessonRepository;
	@Inject
	private LearningPathRepository learningPathRepository;

	private List<Member> members = new ArrayList<>();
	private String memberRole;

	public List<Member> allMembersForOneUser() {
		members = memberRepository.getAllMembersForOneUser(SessionUtils.getConnectedUser().getId());
		return members;
	}

	// Modify to send admin to it's dashboard
	public String goToIndexSchool(Member member) {
		SessionUtils.setConnectedMember(member);
		SessionUtils.setCurrentSchool(member.getSchool());
		if (member instanceof Admin) {
			return "adminDashboard?faces-redirect-true";
		} else {
			return "indexSchool?faces-redirect=true";
		}
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

	public boolean isAdmin(Member member) {
		return member instanceof Admin;
	}

	public List<SynchronousLesson> getFutureSynchronousLessonForUserLikeTeacher() {
		allMembersForOneUser();
		return getFuturSynchronousLessonLikeTeacher();
	}

	public List<SynchronousLesson> getPastSynchronousLessonForUserLikeTeacher() {
		allMembersForOneUser();
		return getPastSynchronousLessonLikeTeacher();
	}

	public List<LearningPath> getFutureSynchronousLessonForUserLikeStudent() {
		allMembersForOneUser();
		List<LearningPath> futurLearningPathList = new ArrayList<>();
		List<LearningPath> allLearningPathList = getSynchronousLessonLikeStudent();

		for (LearningPath learningPath : allLearningPathList) {
			SynchronousLesson synchronousLesson;
			if (learningPath.getActivity() instanceof SynchronousLesson) {
				synchronousLesson = (SynchronousLesson) learningPath.getActivity();
			} else {
				break;
			}
			if (synchronousLesson.getClassDate().isAfter(LocalDateTime.now())) {
				futurLearningPathList.add(learningPath);
			}
		}
		return futurLearningPathList;
	}

	public List<LearningPath> getPastSynchronousLessonForUserLikeStudent() {
		allMembersForOneUser();
		List<LearningPath> pastLearningPathList = new ArrayList<>();
		List<LearningPath> allLearningPathList = getSynchronousLessonLikeStudent();

		for (LearningPath learningPath : allLearningPathList) {
			SynchronousLesson synchronousLesson;
			if (learningPath.getActivity() instanceof SynchronousLesson) {
				synchronousLesson = (SynchronousLesson) learningPath.getActivity();
			} else {
				break;
			}
			if (synchronousLesson.getClassDate().isBefore(LocalDateTime.now())) {
				pastLearningPathList.add(learningPath);
			}
		}
		return pastLearningPathList;
	}

	public List<SynchronousLesson> getFuturSynchronousLessonLikeTeacher() {
		List<SynchronousLesson> synchronousLessonList = new ArrayList<>();
		for (Member member : members) {
			if (member instanceof Teacher) {
				synchronousLessonList
						.addAll(synchronousLessonRepository.getFuturSynchronousLessonsByIdMember(member.getId()));
			}
		}
		return synchronousLessonList;
	}

	public List<SynchronousLesson> getPastSynchronousLessonLikeTeacher() {
		List<SynchronousLesson> synchronousLessonList = new ArrayList<>();
		for (Member member : members) {
			if (member instanceof Teacher) {
				synchronousLessonList
						.addAll(synchronousLessonRepository.getPastSynchronousLessonsByIdMember(member.getId()));
			}
		}
		return synchronousLessonList;
	}

	public List<LearningPath> getSynchronousLessonLikeStudent() {
		List<LearningPath> learningPathList = new ArrayList<>();
		for (Member member : members) {
			if (member instanceof Student) {
				learningPathList.addAll(learningPathRepository.getLearningPathsByUserId(member.getId()));
			}
		}
		return learningPathList;
	}

	public String getStringClassDate(LearningPath learningPath) {
		if (learningPath != null) {
			SynchronousLesson synchronousLesson = (SynchronousLesson) learningPath.getActivity();
			final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			return synchronousLesson.getClassDate().format(customFormatter);
		}
		return "";
	}

	public String getStringDuration(LearningPath learningPath) {
		SynchronousLesson synchronousLesson = (SynchronousLesson) learningPath.getActivity();
		return synchronousLesson.getDuration();
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
