package fr.isika.cda.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.lesson.PhysicalOption;
import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.lesson.UnitStatusEnum;
import fr.isika.cda.entities.lesson.VirtualOption;
import fr.isika.cda.entities.school.*;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.*;

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
	private TeacherRepository teacherRepository;
	@Inject
	private MembershipRepository membershipRepository;
	@Inject
	private VirtualRepository virtualRepository;
	@Inject
	private PhysicalRepository physicalRepository;
	@Inject
	private UnitRepository unitRepository;

	@PostConstruct
	private void initBDD() {
		// creating features to fill subscriptions
		Feature feature1 = new Feature("Cours online",
				"Pour permettre à vos professeurs de proposer des cours en ligne");
		featureRepository.save(feature1);

		Feature feature2 = new Feature("Cours offline",
				"Pour permettre à vos professeurs de proposer des cours en présentiel");
		featureRepository.save(feature2);

		Feature feature3 = new Feature("Outil de paiement",
				"Pour permettre à vos adhérents de régler leurs achats sur la plateforme");
		featureRepository.save(feature3);

		Subscription subscription1 = new Subscription(375.00, 12L, "Premium");
		subscription1.getFeatures().add(feature1);
		subscription1.getFeatures().add(feature2);
		subscription1.getFeatures().add(feature3);
		subscriptionRepository.save(subscription1);

		Subscription subscription2 = new Subscription(170.00, 12L, "Basic");
		subscription2.getFeatures().add(feature1);
		subscriptionRepository.save(subscription2);

		Subscription subscription3 = new Subscription(300.00, 12L, "Normal");
		subscription1.getFeatures().add(feature1);
		subscription1.getFeatures().add(feature2);
		subscriptionRepository.save(subscription3);

		Membership membership1 = new Membership(LocalDateTime.of(2024, Month.JANUARY, 5, 10, 30),
				LocalDateTime.of(2023, Month.JANUARY, 5, 10, 30), subscription1);
		membershipRepository.save(membership1);

		Membership membership2 = new Membership(LocalDateTime.of(2023, Month.MARCH, 9, 17, 20),
				LocalDateTime.of(2022, Month.MARCH, 9, 17, 20), subscription2);
		membershipRepository.save(membership2);

		Membership membership3 = new Membership(LocalDateTime.of(2023, Month.NOVEMBER, 12, 12, 30),
				LocalDateTime.of(2022, Month.NOVEMBER, 12, 12, 30), subscription3);
		membershipRepository.save(membership3);

		Membership membership4 = new Membership(LocalDateTime.of(2023, Month.AUGUST, 10, 11, 30),
				LocalDateTime.of(2024, Month.AUGUST, 10, 11, 30), subscription3);
		membershipRepository.save(membership4);

		Membership membership5 = new Membership(LocalDateTime.of(2023, Month.JUNE, 8, 16, 20),
				LocalDateTime.of(2022, Month.JUNE, 8, 16, 20), subscription1);
		membershipRepository.save(membership5);

		// Create user to set as schools Admin (from user1 to user9 for future)
		User user1 = new User("albert", LocalDateTime.of(2023, Month.JANUARY, 20, 19, 30), RoleTypeEnum.USER,
				new Security("Albert-123"), new Profil("DUPOND", "Albert", "", new Contact("albert@gmail.com",
						"06 06 06 06 06", new Address(12, "Rue du phare", "BREST", "29000"))));
		userRepository.save(user1);

		User user2 = new User("emma2", LocalDateTime.of(2022, Month.DECEMBER, 5, 12, 20, 0), RoleTypeEnum.USER,
				new Security("Emma-123"), new Profil("ROUX", "Emma", "", new Contact("emma@gmail.com", "06 78 78 78 78",
						new Address(952, "Avenue de Paris", "ORLEANS", "45000"))));
		userRepository.save(user2);

		User user3 = new User("bertrand", LocalDateTime.of(2022, Month.NOVEMBER, 10, 21, 10, 30), RoleTypeEnum.USER,
				new Security("Bertrand-123"), new Profil("DUFOUR", "Bertrand", "", new Contact("bertrand@gmail.com",
						"07 57 57 57 25", new Address(52, "Avenue de Lyon", "GRENOBLE", "38000"))));
		userRepository.save(user3);

		User user4 = new User("florence", LocalDateTime.of(2022, Month.SEPTEMBER, 1, 11, 11, 11), RoleTypeEnum.USER,
				new Security("Florence-123"), new Profil("PIAZELLI", "Florence", "", new Contact("florence@gmail.com",
						"07 48 48 48 48", new Address(52, "Place d'Italie", "NICE", "06000"))));
		userRepository.save(user4);

		// Schools need a membership !

		School school1 = new School("Collège des bois", "", "Collège bienveillant et inclusif", new ArrayList<>(),
				new Contact("collegeDesBois@gmail.com", "05 05 05 05 05",
						new Address(789, "Rue du college", "TOULOUSE", "31000")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage1 = new SchoolPage("Collège des bois", new SchoolValue("Collège bienveillant et inclusif",
				"Favoriser la confiance en soi, l’autonomie, tout en permettant à l’enfant d’évoluer à son propre rythme et en toute liberté.",
				""), new Theme("D4C685", "F7EF81", "E5F1EB", FontEnum.ROBOTO.name()));
		school1.setSchoolPage(schoolPage1);
		schoolRepository.save(school1);
		school1.setMembership(membership2);
		createAdmin(user1, school1);

		School school2 = new School("Ecole de la plage", "", "Ecole en plein air", new ArrayList<>(),
				new Contact("ecoledelaplage@gmail.com", "05 36 36 36 36",
						new Address(56, "Rue de la plage", "BIARRITZ", "64200")),
				SchoolTypeEnum.ELEMENTAIRE);
		SchoolPage schoolPage2 = new SchoolPage("Ecole de la plage",
				new SchoolValue("Ecole en plein air",
						"Favoriser l'apprentissage et l'esprit d'équipe par le jeu en plein air.", ""),
				new Theme("FECDAA", "F5F58E", "F8FFF4", FontEnum.UBUNTU.name()));
		school2.setSchoolPage(schoolPage2);
		schoolRepository.save(school2);
		school2.setMembership(membership3);
		createAdmin(user2, school2);

		School school3 = new School(
				"Lycée Beaux Bâtons", "", "Lycée privé", new ArrayList<>(), new Contact("lyceebeauxbatons@gmail.com",
						"04 86 11 23 36", new Address(11, "Avenue des lutins", "PAIMPONT", "35380")),
				SchoolTypeEnum.LYCEE);
		SchoolPage schoolPage3 = new SchoolPage("Lycée Beaux Bâtons", new SchoolValue("Lycée privé",
				"Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.", ""), new Theme());
		school3.setSchoolPage(schoolPage3);
		school3.setMembership(membership1);
		schoolRepository.save(school3);

		School school4 = new School("Collège de la montagne", "", "Collège pour aller plus loin et plus haut",
				new ArrayList<>(), new Contact("collegeDeLaMontagne@gmail.com", "04 06 06 06 06",
						new Address(45, "Rue du sommet", "ALBERTVILLE", "73200")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage4 = new SchoolPage("Au coeur des montagnes",
				new SchoolValue("Le partage en plein air",
						"Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.", ""),
				new Theme("BF1363", "2FABEE", "FBFEF9", FontEnum.PLAYFAIRDISPLAY.name()));
		school4.setSchoolPage(schoolPage4);
		school4.setMembership(membership5);
		schoolRepository.save(school4);
		createAdmin(user3, school4);

		School school5 = new School("Ecole du Port", "", "Ecole où l'on vogue ensemble vers une meilleure connaissance",
				new ArrayList<>(), new Contact("ecoleDuPort@gmail.com", "04 03 03 03 03",
						new Address(17, "Rue de l'embarcadère", "NICE", "06100")),
				SchoolTypeEnum.ELEMENTAIRE);
		SchoolPage schoolPage5 = new SchoolPage("Près de la mère",
				new SchoolValue("Le partage en plein air",
						"Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.", ""),
				new Theme("1B9AAA", "06D6A0", "F8FFE5", FontEnum.ZEYADA.name()));
		school5.setSchoolPage(schoolPage5);
		school5.setMembership(membership4);
		schoolRepository.save(school5);
		createAdmin(user4, school5);

		// Create User for student (from user11 to user19 for future)

		User user11 = new User("louis", LocalDateTime.of(2022, Month.NOVEMBER, 17, 13, 25), RoleTypeEnum.USER,
				new Security("Louis-456"), new Profil("MARCHAND", "Louis", "", new Contact("louis@gmail.com",
						"06 96 69 69 69", new Address(3, "Rue de la gare", "VIERZON", "18100"))));
		userRepository.save(user11);
		createStudent(school1, user11, AcademicLevel.QUATRIEME);
		userRepository.save(user11);
		createStudent(school3, user11, AcademicLevel.TROISIEME);

		User user12 = new User("fleur", LocalDateTime.of(2022, Month.OCTOBER, 25, 6, 6, 6), RoleTypeEnum.USER,
				new Security("Fleur-456"), new Profil("ALBRAND", "Fleur", "", new Contact("fleur@gmail.com",
						"06 41 14 14 14", new Address(41, "Rue des escaliers", "ORLEANS", "45000"))));
		userRepository.save(user12);
		createStudent(school2, user12, AcademicLevel.CM1);

		User user13 = new User("herve", LocalDateTime.of(2022, Month.SEPTEMBER, 5, 9, 9, 9), RoleTypeEnum.USER,
				new Security("Herve-456"), new Profil("LEGRAND", "Hervé", "", new Contact("herve@gmail.com",
						"06 32 32 32 32", new Address(12, "Rue des remparts", "BOURGES", "18000"))));
		userRepository.save(user13);
		createStudent(school3, user13, AcademicLevel.SIXIEME);
		userRepository.save(user13);
		createStudent(school1, user13, AcademicLevel.CINQUIEME);

		User user14 = new User("emilie", LocalDateTime.of(2022, Month.JANUARY, 15, 4, 4, 4), RoleTypeEnum.USER,
				new Security("Emilie-456"), new Profil("GRANDY", "Emilie", "", new Contact("emilie@gmail.com",
						"06 24 24 24 24", new Address(142, "Place de l'Avenir", "NANTES", "44000"))));
		userRepository.save(user14);
		createStudent(school2, user14, AcademicLevel.CM2);
		userRepository.save(user14);
		createStudent(school4, user14, AcademicLevel.CM1);

		// Create user for teacher (from user21 to user29 for future)
		User user21 = new User("jules", LocalDateTime.of(2022, Month.OCTOBER, 19, 13, 25), RoleTypeEnum.USER,
				new Security("Jules-789"), new Profil("TESSIER", "Jules", "", new Contact("jules@gmail.com",
						"06 52 52 52 52", new Address(26, "Rue de la boulangerie", "BRIANCON", "05100"))));
		userRepository.save(user21);
		Teacher teacher1 = createTeacher(school1, user21, SchoolTypeEnum.COLLEGE, SubjectEnum.HISTOIRE);

		User user22 = new User("pauline", LocalDateTime.of(2022, Month.OCTOBER, 25, 6, 6), RoleTypeEnum.USER,
				new Security("Pauline-789"), new Profil("GAUDEL", "Pauline", "", new Contact("pauline@gmail.com",
						"06 62 62 62 62", new Address(74, "Rue du téléphérique", "BORDEAUX", "33000"))));
		userRepository.save(user22);
		Teacher teacher2 = createTeacher(school2, user22, SchoolTypeEnum.ELEMENTAIRE, SubjectEnum.MATHS);

		User user23 = new User("minerva", LocalDateTime.of(2022, Month.JULY, 19, 6, 6), RoleTypeEnum.USER,
				new Security("Minerva-789"), new Profil("MCGONAGALL", "Minerva", "", new Contact("minerva@gmail.com",
						"06 01 19 35 23", new Address(4, "Rue Privet Drive", "DRANCY", "93700"))));
		userRepository.save(user23);
		Teacher teacher3 = createTeacher(school1, user23, SchoolTypeEnum.COLLEGE, SubjectEnum.CHIMIE);

		Teacher teacher4 = createTeacher(school4, user3, SchoolTypeEnum.COLLEGE, SubjectEnum.MATHS);

		// Create some synchronous lessons
		VirtualOption virtual1 = new VirtualOption("www.zoom.fr", "Zoom",
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.CINQUIEME, teacher1,
						"Seigneurs et paysans au Moyen Âge", "1 heure", LocalDateTime.of(2023, Month.MARCH, 12, 14, 30),
						5, new BigDecimal("25")));
		virtualRepository.save(virtual1);

		PhysicalOption physical1 = new PhysicalOption(new Address(13, "Rue de la fontaine", "LYON", "69009"),
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.TROISIEME, teacher1, "La guerre froide",
						"1 heure et quart", LocalDateTime.of(2023, Month.APRIL, 1, 10, 15), 8, new BigDecimal("27")));
		physicalRepository.save(physical1);

		VirtualOption virtual2 = new VirtualOption("www.zoom.fr", "Zoom",
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM1, teacher2, "Les nombres décimaux",
						"1 heure et demi", LocalDateTime.of(2023, Month.MARCH, 24, 10, 30), 3, new BigDecimal("30")));
		virtualRepository.save(virtual2);

		PhysicalOption physical2 = new PhysicalOption(new Address(75, "Place de la mairie", "DIJON", "21000"),
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM2, teacher2, "Les longueurs", "45 minutes",
						LocalDateTime.of(2023, Month.APRIL, 12, 9, 45), 3, new BigDecimal("27")));
		physicalRepository.save(physical2);

		// Create units
		Unit unit6 = new Unit("Théorème de Pythagore",
				"Le théorème de Pythagore s'applique uniquement aux triangles rectangles. \r\n" + "\r\n"
						+ "Un triangle est dit rectangle quand 2 côtés de celui-ci sont perpendiculaires entre eux : \r\n"
						+ "ils forment ainsi un angle droit de 90°. \r\n" + "\r\n"
						+ "Le théorème de Pythagore permet de trouver la mesure d’un côté lorsque l'on connait la mesure des deux autres côtés. \r\n"
						+ "\r\n" + "Selon le théorème de Pythagore : \r\n"
						+ "Dans un triangle ABC rectangle en A, la somme des carrés des 2 côtés AB et AC situés de part et d'autre de l'angle droit est égale au carré de l'hypothénuse BC.\r\n"
						+ "",
				UnitStatusEnum.VALIDATED, SubjectEnum.MATHS, AcademicLevel.QUATRIEME, teacher4);
		unitRepository.save(unit6);

		Unit unit7 = new Unit("Equation du 1er degré à une inconnue",
				"Résoudre une équation à une inconnue x nécessite......",
				UnitStatusEnum.VALIDATED, SubjectEnum.MATHS, AcademicLevel.QUATRIEME, teacher4);
		unitRepository.save(unit7);
		
		Unit unit8 = new Unit("Système d'équation à deux inconnues",
				"Résoudre une équation à 2 inconnues x et y nécessite......",
				UnitStatusEnum.VALIDATED, SubjectEnum.MATHS, AcademicLevel.TROISIEME, teacher4);
		unitRepository.save(unit8);
		
	}

	private void createStudent(School school, User user, AcademicLevel level) {
		Student student = new Student(level);
		student.setUser(user);
		student.setSchool(school);
		studentRepository.save(student);
	}

	private void createAdmin(User user, School school) {
		Admin admin = new Admin();
		admin.setSchool(school);
		admin.setUser(user);
		memberRepository.save(admin);
	}

	private Teacher createTeacher(School school, User user, SchoolTypeEnum schoolTypeEnum, SubjectEnum subjectEnum) {
		Teacher teacher = new Teacher(schoolTypeEnum, subjectEnum);
		teacher.setUser(user);
		teacher.setSchool(school);
		teacherRepository.save(teacher);
		return teacher;
	}

}
