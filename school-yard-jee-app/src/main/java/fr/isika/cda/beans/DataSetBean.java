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
import fr.isika.cda.entities.lesson.VirtualOption;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.Membership;
import fr.isika.cda.entities.school.School;
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

		Membership membership1 = new Membership(LocalDateTime.of(2024, Month.JANUARY, 5, 10, 30), LocalDateTime.of(2023, Month.JANUARY, 5, 10, 30), subscription1);
		membershipRepository.save(membership1);

		Membership membership2 = new Membership(LocalDateTime.of(2023, Month.MARCH, 9, 17, 20), LocalDateTime.of(2022, Month.MARCH, 9, 17, 20), subscription2);
		membershipRepository.save(membership2);

		Membership membership3 = new Membership(LocalDateTime.of(2023, Month.NOVEMBER, 12, 12, 30), LocalDateTime.of(2022, Month.NOVEMBER, 12, 12, 30), subscription1);
		membershipRepository.save(membership3);

		// Create user to set as schools admin
		User user1 = new User("albert", LocalDateTime.of(2023, Month.JANUARY, 20, 19, 30), RoleTypeEnum.USER,
				new Security("123"), new Profil("DUPOND", "Albert", "", new Contact("albert@gmail.com", "0606060606",
						new Address(12, "rue du phare", "Brest", "29000"))));
		userRepository.save(user1);

        User user2 = new User("emma",
                LocalDateTime.of(2022,
                        Month.DECEMBER, 5, 12, 20, 0),
                RoleTypeEnum.USER,
                new Security("aze"),
                new Profil("ROUX",
                        "Emma",
                        "",
                        new Contact("emma@gmail.com",
                                "06 78 78 78 78",
                                new Address(952, "Avenue de Paris", "ORLEANS", "45000"))));
        userRepository.save(user2);

        User user3 = new User("bertrand",
                LocalDateTime.of(2022,
                        Month.NOVEMBER, 10, 21, 10, 30),
                RoleTypeEnum.USER,
                new Security("rtr"),
                new Profil("DUFOUR",
                        "Bertrand",
                        "",
                        new Contact("bertrand@gmail.com",
                                "07 57 57 57 25",
                                new Address(52, "Avenue de Lyon", "GRENOBLE", "38000"))));
        userRepository.save(user3);

        User user4 = new User("florence",
                LocalDateTime.of(2022,
                        Month.SEPTEMBER, 1, 11, 11, 11),
                RoleTypeEnum.USER,
                new Security("lrc"),
                new Profil("PIAZELLI",
                        "Florence",
                        "",
                        new Contact("florence@gmail.com",
                                "07 48 48 48 48",
                                new Address(52, "Place d'Italie", "NICE", "06000"))));
        userRepository.save(user4);

		School school1 = new School("Collège des bois", "", "Collège bienveillant et inclusif", new ArrayList<>(),
				new Contact("collegeDesBois@gmail.com", "05 05 05 05 05",
						new Address(789, "Rue du college", "TOULOUSE", "31000")),
				SchoolTypeEnum.COLLEGE);
		schoolRepository.save(school1);
		school1.setMembership(membership2);
		createAdmin(user1, school1);

		School school2 = new School("Ecole de la plage", "", "Ecole en plein air", new ArrayList<>(),
				new Contact("ecoledelaplage@gmail.com", "05 36 36 36 36",
						new Address(56, "Rue de la plage", "BIARRITZ", "64200")),
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

        School school4 = new School("Collège de la montagne",
                "",
                "Collège pour aller plus loin et plus haut",
                new ArrayList<>(),
                new Contact("collegeDeLaMontagne@gmail.com",
                        "04 06 06 06 06",
                        new Address(45, "Rue du sommet", "ALBERTVILLE", "73200")),
                SchoolTypeEnum.COLLEGE);
        schoolRepository.save(school4);
        createAdmin(user3, school4);

        School school5 = new School("Ecole du Port",
                "",
                "Ecole où l'on vogue ensemble vers une meilleure connaissance",
                new ArrayList<>(),
                new Contact("ecoleDuPort@gmail.com",
                        "04 03 03 03 03",
                        new Address(17, "Rue de l'embarcadère", "NICE", "06100")),
                SchoolTypeEnum.ELEMENTAIRE);
        schoolRepository.save(school5);
        createAdmin(user4, school5);

		// Create User for student (from user11 to user19 for futur)

		User user11 = new User("louis", LocalDateTime.of(2022, Month.NOVEMBER, 17, 13, 25), RoleTypeEnum.USER,
				new Security("456"), new Profil("Marchand", "Louis", "", new Contact("louis@gmail.com", "0696696969",
						new Address(3, "rue de la gare", "Vierzon", "18100"))));
		userRepository.save(user11);
		createStudent(school1, user11, AcademicLevel.QUATRIEME);
        userRepository.save(user11);
        createStudent(school3, user11, AcademicLevel.TROISIEME);

        User user12 = new User("fleur",
                LocalDateTime.of(2022,
                        Month.OCTOBER, 25, 6, 6, 6),
                RoleTypeEnum.USER,
                new Security("qsd"),
                new Profil("ALBRAND",
                        "Fleur",
                        "",
                        new Contact("fleur@gmail.com",
                                "06 41 14 14 14",
                                new Address(41, "Rue des escaliers", "ORLEANS", "45000"))));
        userRepository.save(user12);
        createStudent(school2, user12, AcademicLevel.CM1);

        User user13 = new User("herve",
                LocalDateTime.of(2022,
                        Month.SEPTEMBER, 5, 9, 9, 9),
                RoleTypeEnum.USER,
                new Security("hrv"),
                new Profil("LEGRAND",
                        "Hervé",
                        "",
                        new Contact("herve@gmail.com",
                                "06 32 32 32 32",
                                new Address(12, "Rue des remparts", "BOURGES", "18000"))));
        userRepository.save(user13);
        createStudent(school3, user13, AcademicLevel.SIXIEME);
        userRepository.save(user13);
        createStudent(school1, user13, AcademicLevel.CINQUIEME);

        User user14 = new User("emilie",
                LocalDateTime.of(2022,
                        Month.JANUARY, 15, 4, 4, 4),
                RoleTypeEnum.USER,
                new Security("mle"),
                new Profil("GRANDY",
                        "Emilie",
                        "",
                        new Contact("emilie@gmail.com",
                                "06 24 24 24 24",
                                new Address(142, "Place de l'Avenir","NANTES", "44000"))));
        userRepository.save(user14);
        createStudent(school2, user14, AcademicLevel.CM2);
        userRepository.save(user14);
        createStudent(school4, user14, AcademicLevel.CM1);

        // Create User for student (from user21 to user29 for futur)
        User user21 = new User("jules", LocalDateTime.of(2022, Month.OCTOBER, 19, 13, 25), RoleTypeEnum.USER,
                new Security("789"), new Profil("Tessier", "Jules", "", new Contact("jules@gmail.com", "0652525252",
                new Address(26, "rue de la boulangerie", "Briançon", "05100"))));
        userRepository.save(user21);
        Teacher  teacher1 = createTeacher(school1, user21, SchoolTypeEnum.COLLEGE, SubjectEnum.HISTOIRE);

        User user22 = new User("pauline", LocalDateTime.of(2022, Month.OCTOBER, 25, 6, 6), RoleTypeEnum.USER,
                new Security("wxc"), new Profil("Gaudel", "Pauline", "", new Contact("pauline@gmail.com", "0662626262",
                new Address(74, "rue du téléphérique", "Bordeaux", "33000"))));
        userRepository.save(user22);
        Teacher teacher2 = createTeacher(school2, user22, SchoolTypeEnum.ELEMENTAIRE, SubjectEnum.MATHS);

        //Create some synchronous lessons
        VirtualOption virtual1 = new VirtualOption("Zoom",
                "www.zoom.fr",
                new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.CINQUIEME, teacher1, "Seigneurs et paysans au Moyen Âge", "1 heure", LocalDateTime.of(2023, Month.MARCH, 12, 14, 30), 5, new BigDecimal("25")));
        virtualRepository.save(virtual1);

        PhysicalOption physical1 = new PhysicalOption(new Address(13,"rue de la fontaine", "Lyon", "69009"),
                new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.TROISIEME, teacher1, "La guerre froide", "1 heure et quart", LocalDateTime.of(2023, Month.APRIL, 1, 10, 15), 8, new BigDecimal("27")));
        physicalRepository.save(physical1);

        VirtualOption virtual2= new VirtualOption("Zoom",
                "www.zoom.fr",
                new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM1, teacher2, "Les nombres décimaux", "1 heure et demi", LocalDateTime.of(2023, Month.MARCH, 24, 10, 30), 3, new BigDecimal("30")));
        virtualRepository.save(virtual2);

        PhysicalOption physical2 = new PhysicalOption(new Address(75,"place de la mairie", "Dijon", "21000"),
                new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM2, teacher2, "Les longueurs", "45 minutes", LocalDateTime.of(2023, Month.APRIL, 12, 9, 45), 3, new BigDecimal("27")));
        physicalRepository.save(physical2);
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

