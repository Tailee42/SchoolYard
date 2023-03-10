package fr.isika.cda.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

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
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.SubscriptionRepository;
import fr.isika.cda.utils.FileUpload;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class CreateSchoolBean {

	private School school = new School();

	private String logoFileName;

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
		school.setStatusSchool(StatusSchool.TOPUBLISH);
		school.setLogo(logoFileName);
		schoolRepository.save(school);
		memberRepository.save(createAdmin());

		return "schoolMembershipForm";
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
		LocalDate startingDate = LocalDate.now();
		membership.setStartingDate(startingDate);
		membership.setEndingDate(startingDate.plusMonths(subscriptionDuration));
	}

	private void resetAll() {
		school = new School();
		membership = new Membership();
	}

	public void uploadFile(FileUploadEvent event) {
		String timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_hhmmss"));
		UploadedFile file = event.getFile();
		logoFileName = timestamp + "_" + file.getFileName();
		FileUpload.doUpLoad(file, logoFileName);
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

	public void autofill() {
		school = new School("Lyc??e de la Prairie", "", "Semons ensemble les graines de notre avenir !", new ArrayList<>(),
				new Contact("lyceeDeLaPrairie@gmail.com", "01 25 26 27 28", new Address(8, "rue des Fleurs", "Chartres", "28000")),
				SchoolTypeEnum.LYCEE);
	}

	public List<Subscription> subscriptions() {
		return subscriptionRepository.getAll();
	}

	public Map<String, SchoolTypeEnum> levels() {
		return SchoolTypeEnum.levels;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getLogoFile() {
		return logoFileName;
	}

	public void setLogoFile(String logoFile) {
		this.logoFileName = logoFile;
	}

	public Membership getMembership() {
		return membership;
	}

}
