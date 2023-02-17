package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.isika.cda.entities.AcademicLevel;
import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Membership;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.FeatureRepository;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.MembershipRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.repositories.SubscriptionRepository;
import fr.isika.cda.repositories.UserRepository;

@Startup
@Singleton
public class DataSetBean {

	@Inject
	private StudentRepository studentRepository;
	@Inject
	private UserRepository userRepository;
	@Inject
	private MemberRepository memberRepository;
	@Inject
	private SchoolRepository schoolRepository;
	@Inject
	private FeatureRepository featureRepository;
	@Inject
	private SubscriptionRepository subscriptionRepository;
	@Inject
	private MembershipRepository membershipRepository;
	

	@PostConstruct
	private void initBDD() {
		//creating features to fill subscriptions 
		Feature feature1 = new Feature("Cours online",
				"Pour permettre à vos professeurs de proposer des cours en ligne");
		featureRepository.save(feature1);

		Feature feature2 = new Feature("Cours offline",
				"Pour permettre à vos professeurs de proposer des cours en presentiel");
		featureRepository.save(feature2);

		Feature feature3 = new Feature("Outil de paiement",
				"Pour permettre à vos adhérants de régler leurs achats sur la plateforme");
		featureRepository.save(feature3);
		
		Subscription subscription1 = new Subscription(375.00, 12, "Premium");
		subscription1.getFeatures().add(feature3);
		subscriptionRepository.save(subscription1);
		
		Subscription subscription2 = new Subscription(170.00, 12, "Basic");
		subscription2.getFeatures().add(feature1);
		subscriptionRepository.save(subscription2);
		
		Membership membership1 = new Membership(LocalDateTime.of(2024, Month.JANUARY, 5, 10, 30, 13), LocalDateTime.of(2023, Month.JANUARY, 5, 10, 30, 13), subscription1);
		membershipRepository.save(membership1);
		
		Membership membership2 = new Membership(LocalDateTime.of(2023, Month.MARCH, 9, 17, 20, 03), LocalDateTime.of(2022, Month.MARCH, 9, 17, 20, 03), subscription2);
		membershipRepository.save(membership2);
		
		Membership membership3 = new Membership(LocalDateTime.of(2023, Month.NOVEMBER, 12, 12, 30, 13), LocalDateTime.of(2022, Month.NOVEMBER, 12, 12, 30, 13), subscription1);
		membershipRepository.save(membership3);
		
		// Create user to set as schools admin
		User user1 = new User("albert", LocalDateTime.of(2023, Month.JANUARY, 20, 19, 30, 40), RoleTypeEnum.USER,
				new Security("123"), new Profil("Dupond", "Albert", "", new Contact("albert@gmail.com", "0606060606",
						new Address(12, "rue du phare", "Brest", "29000"))));
		userRepository.save(user1);

		User user2 = new User("emma", LocalDateTime.of(2022, Month.DECEMBER, 5, 12, 20, 0), RoleTypeEnum.USER,
				new Security("aze"), new Profil("Roux", "Emma", "", new Contact("emma@gmail.com", "0678787878",
						new Address(952, "avenue de Paris", "Orléans", "45000"))));
		userRepository.save(user2);
		
		//Create schools 
		
		School school1 = new School("Collège des bois", "", "Collège bienviellant et inclusif", new ArrayList<>(),
				new Contact("collegeDesBois@gmail.com", "0405050505",
						new Address(789, "rue du college", "Toulouse", "31000")),
				SchoolTypeEnum.COLLEGE);
		schoolRepository.save(school1);
		school1.setMembership(membership2);
		createAdmin(user1, school1);

		School school2 = new School("Ecole de la plage", "", "Ecole en plein air", new ArrayList<>(),
				new Contact("ecoledelaplage@gmail.com", "0236363636",
						new Address(56, "rue de la plage", "Biarritz", "64200")),
				SchoolTypeEnum.ELEMENTAIRE);
		schoolRepository.save(school2);
		school2.setMembership(membership3);
		createAdmin(user2, school2);
		
		School school3 = new School("Lycée BeauxBâtons", "", "Lycée privé", new ArrayList<>(),
				new Contact("lyceebeauxbatons@gmail.com", "0486112336",
						new Address(11, "Avenue des lutins", "Paimpont", "35380")),
				SchoolTypeEnum.LYCEE);
		school3.setMembership(membership1);
		schoolRepository.save(school3);

		// Create User for student
		User user3 = new User("louis", LocalDateTime.of(2022, Month.NOVEMBER, 17, 13, 25, 0), RoleTypeEnum.USER,
				new Security("456"), new Profil("Marchand", "Louis", "", new Contact("louis@gmail.com", "0696696969",
						new Address(3, "rue de la gare", "Vierzon", "18100"))));
		userRepository.save(user3);
		createStudent(school1, user3, AcademicLevel.QUATRIEME);

		User user4 = new User("fleur", LocalDateTime.of(2022, Month.OCTOBER, 25, 6, 6, 6), RoleTypeEnum.USER,
				new Security("qsd"), new Profil("Albrand", "Fleur", "", new Contact("fleur@gmail.com", "0641141414",
						new Address(41, "rue des escaliers", "Orléans", "45000"))));
		userRepository.save(user4);
		createStudent(school2, user4, AcademicLevel.CM1);
			
	}

	private void createStudent(School school1, User user3, AcademicLevel level) {
		Student student = new Student(level);
		student.setUser(user3);
		student.setSchool(school1);
		studentRepository.save(student);
	}

	private void createAdmin(User user1, School school1) {
		Admin admin = new Admin();
		admin.setSchool(school1);
		admin.setUser(user1);
		memberRepository.save(admin);
	}

}
