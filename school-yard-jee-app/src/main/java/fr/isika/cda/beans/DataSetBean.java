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
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.FeatureRepository;
import fr.isika.cda.repositories.MemberRepository;
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

	@PostConstruct
	private void initBDD() {

		// Create user to set as schools admin
		User user1 = new User("albert", LocalDateTime.of(2023, Month.JANUARY, 20, 19, 30, 40), RoleTypeEnum.USER,
				new Security("123"), new Profil("Dupond", "Albert", "", new Contact("albert@gmail.com", "0606060606",
						new Address(12, "rue du phare", "Brest", "29000"))));
		userRepository.save(user1);

		User user2 = new User("emma", LocalDateTime.of(2022, Month.DECEMBER, 5, 12, 20, 0), RoleTypeEnum.USER,
				new Security("aze"), new Profil("Roux", "Emma", "", new Contact("emma@gmail.com", "0678787878",
						new Address(952, "avenue de Paris", "Orléans", "45000"))));
		userRepository.save(user2);

		School school1 = new School("Collège des bois", "", "Collège bienviellant et inclusif", new ArrayList<>(),
				new Contact("collegeDesBois@gmail.com", "0405050505",
						new Address(789, "rue du college", "Toulouse", "31000")),
				SchoolTypeEnum.COLLEGE);
		schoolRepository.save(school1);

		createAdmin(user1, school1);

		School school2 = new School("école de la plage", "", "école en plein air", new ArrayList<>(),
				new Contact("écoledelaplage@gmail.com", "0236363636",
						new Address(56, "rue de la plage", "Biarritz", "64200")),
				SchoolTypeEnum.ELEMENTAIRE);
		schoolRepository.save(school2);
		createAdmin(user2, school2);

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

		Subscription subscription1 = new Subscription(375.00, 6, "Premium");
		subscription1.getFeatures().add(feature3);
		subscriptionRepository.save(subscription1);

		Subscription subscription2 = new Subscription(170.00, 3, "Basic");
		subscription2.getFeatures().add(feature1);
		subscriptionRepository.save(subscription2);

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
