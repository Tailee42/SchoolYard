package fr.isika.cda.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Membership;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.StatusSchool;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.FeatureRepository;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.MembershipRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.SubscriptionRepository;
import fr.isika.cda.utils.FileUtils;
import fr.isika.cda.utils.SessionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class CreateSchoolBean {

	private School school = new School();

	private Part logoFile;

	private Membership membership = new Membership();

	@Inject
	private SchoolRepository schoolRepository;

	@Inject
	private MemberRepository memberRepository;

	@Inject
	private SubscriptionRepository subscriptionRepository;

	@Inject
	private FeatureRepository featureRepository;

	public String create() {
		// TODO : ecrire le fichier sur le disque
		school.setLogo(createImagePath());
		school.setStatusSchool(StatusSchool.TOPUBLISH);
		schoolRepository.save(school);
		Admin admin = createAdmin();
		memberRepository.save(admin);

		return "schoolMembershipForm";
	}
	
	private String createImagePath() {
		FileUtils.initResourcesDir();

		String uuid = UUID.randomUUID().toString();
		String filename = uuid + ".png";
		return "/school-yard-resources/images/" + filename;
	}

	public String setMembership(Subscription subscription) {
		membership.setSubscription(subscription);
		initMembershipDuration(subscription.getDuration());
		school.setMembership(membership);
		schoolRepository.update(school);

		resetAll();

		return "userDashboard?faces-redirect=true";
	}
	
	private void initMembershipDuration(Long subscriptionDuration) {
		LocalDateTime startingDate = LocalDateTime.now();
		membership.setStartingDate(startingDate);
		membership.setEndingDate(startingDate.plusMonths(subscriptionDuration));
	}
	

	private void resetAll() {
		school = new School();
		membership = new Membership();
	}

	private Admin createAdmin() {
		Admin admin = new Admin();
		User user = SessionUtils.getConnectedUser();
		admin.setSchool(school);
		admin.setUser(user);
		return admin;
	}

	public List<Feature> featuresBySubscription(Long subscriptionId) {
		return featureRepository.getFeatureBySubscriptionId(subscriptionId);
	}

	public List<Subscription> subscriptions() {
		return subscriptionRepository.getAll();
	}

	public SchoolTypeEnum[] levels() {
		return SchoolTypeEnum.values();
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Part getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(Part logoFile) {
		this.logoFile = logoFile;
	}

	public Membership getMembership() {
		return membership;
	}

}
