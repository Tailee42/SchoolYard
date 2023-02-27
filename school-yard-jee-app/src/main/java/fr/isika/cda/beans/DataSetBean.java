package fr.isika.cda.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.PhysicalOption;
import fr.isika.cda.entities.lesson.SynchronousLesson;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.lesson.UnitStatusEnum;
import fr.isika.cda.entities.lesson.VirtualOption;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.FontEnum;
import fr.isika.cda.entities.school.Membership;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.school.SchoolPage;
import fr.isika.cda.entities.school.SchoolValue;
import fr.isika.cda.entities.school.StatusSchool;
import fr.isika.cda.entities.school.Theme;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.subscription.Feature;
import fr.isika.cda.entities.subscription.Subscription;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.entities.teacher.TeacherStatusEnum;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.SuperAdmin;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.FeatureRepository;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.MembershipRepository;
import fr.isika.cda.repositories.PhysicalRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.repositories.SubscriptionRepository;
import fr.isika.cda.repositories.SuperAdminRepository;
import fr.isika.cda.repositories.TeacherRepository;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.repositories.UserRepository;
import fr.isika.cda.repositories.VirtualRepository;

@Startup
@Singleton
public class DataSetBean {

	public static final String NO_LOGO_PNG = "no_logo.png";

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
	@Inject
	private LearningPathRepository learningPathRepository;

	@Inject
	private SuperAdminRepository superAdminRepository;

	@PostConstruct
	private void initBDD() {
		// creating a superAdmin to Rule them All
		SuperAdmin superAdmin = new SuperAdmin("master", "passWorld",
				new Profil("RUSARD", "Argus", "", new Contact("argus@cracmol.com", "07 19 73 35 69",
						new Address(4, "Privet Drive", "Little-Whinging", "CR3 0AA"))));
		superAdminRepository.save(superAdmin);

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
		subscription3.getFeatures().add(feature1);
		subscription3.getFeatures().add(feature2);
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

		User user1 = new User("albert", LocalDate.of(2023, Month.JANUARY, 20), RoleTypeEnum.USER,
				new Security("Albert-123"), new Profil("DUPOND", "Albert", "", new Contact("albert@gmail.com",
						"06 54 77 05 06", new Address(12, "Faubourg de la madeleine ", "BLAGNAC", "31700"))));
		userRepository.save(user1);

		User user2 = new User("emma2", LocalDate.of(2022, Month.DECEMBER, 5), RoleTypeEnum.USER,
				new Security("Emma-123"), new Profil("ROUX", "Emma", "", new Contact("emma@gmail.com", "06 78 41 23 70",
						new Address(952, "Avenue de Paris", "ANGLET", "64600"))));
		userRepository.save(user2);

		User user3 = new User("bertrand", LocalDate.of(2022, Month.NOVEMBER, 10), RoleTypeEnum.USER,
				new Security("Bertrand-123"), new Profil("DUFOUR", "Bertrand", "", new Contact("bertrand@gmail.com",
						"07 57 25 73 25", new Address(52, "Avenue de Rennes", "PLOERMEL", "56800"))));
		userRepository.save(user3);

		User user4 = new User("florence", LocalDate.of(2022, Month.SEPTEMBER, 1), RoleTypeEnum.USER,
				new Security("Florence-123"), new Profil("PIAZELLI", "Florence", "", new Contact("florence@gmail.com",
						"07 48 75 24 48", new Address(52, "Place du marché au bois", "TOURS-EN-SAVOIE", "73290"))));
		userRepository.save(user4);

		User user5 = new User("beatrice", LocalDate.of(2023, Month.FEBRUARY, 07), RoleTypeEnum.USER,
				new Security("Beatrice-123"), new Profil("DUPONT", "Beatrice", "", new Contact("beatrice@gmail.com",
						"07 58 04 48 80", new Address(141, "Place d'Italie", "VILLEFRANCE-SUR-MER", "06230"))));
		userRepository.save(user5);

		// Schools need a membership !

		School school1 = new School("Collège des bois", "Logo_Crop_CollegeDesBois.png",
				"Le collège où les connaissances prennent racine", new ArrayList<>(),
				new Contact("collegeDesBois@gmail.com", "05 05 05 05 05",
						new Address(789, "Rue du college", "TOULOUSE", "31000")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage1 = new SchoolPage(
				"Le Collège des Bois est un établissement d'enseignement réputé qui offre une éducation de qualité à tous les élèves. Avec un corps enseignant expérimenté et des installations modernes, le collège s'efforce de fournir un environnement éducatif stimulant qui encourage les élèves à se développer académiquement et personnellement. Le slogan du collège, \"Le collège où les connaissances prennent racine\", illustre la conviction que l'éducation est un processus continu qui nécessite une base solide pour se développer. En offrant un enseignement de qualité qui prend en compte les besoins individuels de chaque élève, Le Collège des Bois aide les élèves à développer les compétences nécessaires pour réussir dans leur vie future.",
				new SchoolValue("Le collège où les connaissances prennent racine",
						"Favoriser la confiance en soi, l’autonomie, tout en permettant à l’enfant d’évoluer à son propre rythme et en toute liberté.",
						""),
				new Theme("D4C685", "F7EF81", "E5F1EB", FontEnum.ROBOTO.name()));
		school1.setSchoolPage(schoolPage1);
		school1.setStatusSchool(StatusSchool.PUBLISHED);
		school1.setMembership(membership3);
		schoolRepository.save(school1);
		createAdmin(user1, school1);

		School school2 = new School("Ecole de la plage", "Logo_Crop_EcoleDeLaPlage.png",
				"Se détendre pour mieux apprendre", new ArrayList<>(), new Contact("ecoledelaplage@gmail.com",
						"05 36 36 36 36", new Address(56, "Rue de la plage", "BIARRITZ", "64200")),
				SchoolTypeEnum.ELEMENTAIRE);
		SchoolPage schoolPage2 = new SchoolPage(
				"L'Ecole de la Plage est un établissement éducatif unique en son genre, offrant aux élèves la possibilité d'apprendre tout en profitant du cadre enchanteur de la plage. Avec des enseignants passionnés et des programmes pédagogiques innovants, l'école encourage les élèves à explorer leur curiosité, à développer leurs compétences et à s'épanouir tout en profitant de la détente que procure la vue de la mer. L'école offre une expérience éducative complète, intégrant une variété d'activités extrascolaires pour encourager le développement personnel et social. Que ce soit pour une journée, une semaine ou une année scolaire, l'Ecole de la Plage est l'endroit idéal pour apprendre, se détendre et s'amuser dans un environnement inspirant et stimulant.",
				new SchoolValue("Se détendre pour mieux apprendre",
						"Favoriser l'apprentissage et l'esprit d'équipe par le jeu en plein air.", ""),
				new Theme("FECDAA", "F5F58E", "F8FFF4", FontEnum.UBUNTU.name()));
		school2.setSchoolPage(schoolPage2);
		school2.setStatusSchool(StatusSchool.PUBLISHED);
		school2.setMembership(membership3);
		schoolRepository.save(school2);
		createAdmin(user2, school2);

		School school3 = new School("Lycée Beaux Bâtons", "LogoLyceeBeauxBatons.png", "Lycée privé", new ArrayList<>(),
				new Contact("lyceebeauxbatons@gmail.com", "02 86 11 23 36",
						new Address(11, "Avenue des lutins", "PAIMPONT", "35380")),
				SchoolTypeEnum.LYCEE);
		SchoolPage schoolPage3 = new SchoolPage("Le lycée Beaux Bâtons est un établissement formant les jeunes élèves à l'art et à la pratique de l'apprentissage.", new SchoolValue("Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.","" ,
				""), new Theme("6CA7E6", "A6A9AB", "F9F8F8", FontEnum.UBUNTU.name()));
		school3.setSchoolPage(schoolPage3);
		school3.setStatusSchool(StatusSchool.PUBLISHED);
		school3.setMembership(membership1);
		schoolRepository.save(school3);
		createAdmin(user3, school3);

		School school4 = new School("Collège de la montagne", "Logo_Crop_CollegeDeLaMontagne.png",
				"Hissons nos ambitions vers les sommets", new ArrayList<>(),
				new Contact("collegeDeLaMontagne@gmail.com", "04 06 06 06 06",
						new Address(45, "Rue du sommet", "ALBERTVILLE", "73200")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage4 = new SchoolPage(
				"Le Collège de la Montagne est une institution éducative reconnue pour sa qualité académique et sa vocation à former les leaders de demain. Situé dans un environnement naturel exceptionnel, au pied des montagnes, l'établissement offre à ses élèves un cadre d'apprentissage stimulant et inspirant. Les enseignants, expérimentés et passionnés, dispensent des programmes pédagogiques novateurs et adaptés aux besoins de chaque élève, dans un environnement propice à l'excellence académique. Le Collège de la Montagne est également un lieu de vie, où les élèves peuvent développer leur personnalité et leur créativité grâce à un large éventail d'activités extrascolaires, sportives et culturelles. L'établissement est ainsi un lieu d'épanouissement, où les élèves apprennent à se dépasser et à atteindre les sommets de leur potentiel.",
				new SchoolValue("Le partage en plein air",
						"Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.", ""),
				new Theme("BF1363", "2FABEE", "FBFEF9", FontEnum.PLAYFAIRDISPLAY.name()));
		school4.setSchoolPage(schoolPage4);
		school4.setStatusSchool(StatusSchool.PUBLISHED);
		school4.setMembership(membership5);
		schoolRepository.save(school4);
		createAdmin(user4, school4);

		School school5 = new School("Ecole du Port", "LogoEcoleDuPortNoName.png",
				"Ecole où l'on vogue ensemble vers une meilleure connaissance", new ArrayList<>(),
				new Contact("ecoleDuPort@gmail.com", "04 03 03 03 03",
						new Address(17, "Rue de l'embarcadère", "NICE", "06100")),
				SchoolTypeEnum.ELEMENTAIRE);
		SchoolPage schoolPage5 = new SchoolPage("Aucune crainte, avec nous, votre réussite scolaire est assurée ! L'air marin procure des effets spectaculaires sur la mémoire et la rapidité de réalisation de ses devoirs ! ",
				new SchoolValue("Ecole où l'on vogue ensemble vers une meilleure connaissance", "", ""),
				new Theme("c8f5fa", "21c493", "f7f3ed", FontEnum.ZEYADA.name()));
		school5.setSchoolPage(schoolPage5);
		school5.setStatusSchool(StatusSchool.PUBLISHED);
		school5.setMembership(membership4);
		schoolRepository.save(school5);
		createAdmin(user5, school5);

		// Create User for student (from user11 to user19 for future)

		User user11 = new User("louis", LocalDate.of(2022, Month.NOVEMBER, 17), RoleTypeEnum.USER,
				new Security("Louis-123"), new Profil("MARCHAND", "Louis", "", new Contact("louis@gmail.com",
						"06 96 69 69 69", new Address(3, "Rue de la gare", "VIERZON", "18100"))));
		userRepository.save(user11);
		Student student1 = createStudent(school1, user11, AcademicLevel.QUATRIEME);
		userRepository.save(user11);
		Student student2 = createStudent(school4, user11, AcademicLevel.TROISIEME);

		User user12 = new User("fleur", LocalDate.of(2022, Month.OCTOBER, 25), RoleTypeEnum.USER,
				new Security("Fleur-123"), new Profil("ALBRAND", "Fleur", "", new Contact("fleur@gmail.com",
						"06 41 14 14 14", new Address(41, "Rue des escaliers", "ORLEANS", "45000"))));
		userRepository.save(user12);
		Student student3 = createStudent(school2, user12, AcademicLevel.CM1);

		User user13 = new User("herve", LocalDate.of(2022, Month.SEPTEMBER, 5), RoleTypeEnum.USER,
				new Security("Herve-123"), new Profil("LEGRAND", "Hervé", "", new Contact("herve@gmail.com",
						"06 32 32 32 32", new Address(12, "Rue des remparts", "BOURGES", "18000"))));
		userRepository.save(user13);
		Student student4 = createStudent(school3, user13, AcademicLevel.SECONDE);
		userRepository.save(user13);
		Student student5 = createStudent(school1, user13, AcademicLevel.TROISIEME);

		User user14 = new User("emilie", LocalDate.of(2022, Month.JANUARY, 15), RoleTypeEnum.USER,
				new Security("Emilie-123"), new Profil("GRANDY", "Emilie", "", new Contact("emilie@gmail.com",
						"06 24 24 24 24", new Address(142, "Place de l'Avenir", "NANTES", "44000"))));
		userRepository.save(user14);
		Student student6 = createStudent(school2, user14, AcademicLevel.CM2);
		userRepository.save(user14);
		Student student7 = createStudent(school4, user14, AcademicLevel.CM1);

		// Create user for teacher (from user21 to user29 for future)

		User user21 = new User("jules", LocalDate.of(2022, Month.OCTOBER, 19), RoleTypeEnum.USER,
				new Security("Jules-123"), new Profil("TESSIER", "Jules", "", new Contact("jules@gmail.com",
						"06 52 52 52 52", new Address(26, "Rue de la boulangerie", "BRIANCON", "05100"))));
		userRepository.save(user21);
		Teacher teacher1 = createTeacher(school1, user21, SchoolTypeEnum.COLLEGE, SubjectEnum.HISTOIRE);

		User user22 = new User("pauline", LocalDate.of(2022, Month.OCTOBER, 25), RoleTypeEnum.USER,
				new Security("Pauline-123"), new Profil("GAUDEL", "Pauline", "", new Contact("pauline@gmail.com",
						"06 62 62 62 62", new Address(74, "Rue du téléphérique", "BORDEAUX", "33000"))));
		userRepository.save(user22);
		Teacher teacher2 = createTeacher(school2, user22, SchoolTypeEnum.ELEMENTAIRE, SubjectEnum.MATHS);

		User user23 = new User("minerva", LocalDate.of(2022, Month.JULY, 19), RoleTypeEnum.USER,
				new Security("Minerva-123"), new Profil("MCGONAGALL", "Minerva", "", new Contact("minerva@gmail.com",
						"06 01 19 35 23", new Address(4, "Rue Privet Drive", "DRANCY", "93700"))));
		userRepository.save(user23);
		Teacher teacher3 = createTeacher(school1, user23, SchoolTypeEnum.COLLEGE, SubjectEnum.CHIMIE);

		Teacher teacher4 = createTeacher(school4, user4, SchoolTypeEnum.COLLEGE, SubjectEnum.MATHS);

		Teacher teacher5 = createTeacher(school4, user21, SchoolTypeEnum.COLLEGE, SubjectEnum.HISTOIRE);

		Teacher teacher6 = createTeacher(school3, user4, SchoolTypeEnum.LYCEE, SubjectEnum.MATHS);

		// Create some synchronous lessons

		VirtualOption virtual1 = new VirtualOption("www.zoom.us", "Zoom",
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.CINQUIEME, teacher1,
						"Seigneurs et paysans au Moyen Âge", "1 heure", LocalDateTime.of(2023, Month.MARCH, 12, 14, 30),
						5, new BigDecimal("25")));
		virtualRepository.save(virtual1);

		PhysicalOption physical1 = new PhysicalOption(new Address(13, "Rue de la fontaine", "LYON", "69009"),
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.TROISIEME, teacher1, "La guerre froide",
						"1 heure et quart", LocalDateTime.of(2023, Month.APRIL, 1, 10, 15), 8, new BigDecimal("27")));
		physicalRepository.save(physical1);

		VirtualOption virtual2 = new VirtualOption("www.zoom.us", "Zoom",
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM1, teacher2, "Les nombres décimaux",
						"1 heure et demi", LocalDateTime.of(2023, Month.MARCH, 24, 10, 30), 3, new BigDecimal("30")));
		virtualRepository.save(virtual2);

		PhysicalOption physical2 = new PhysicalOption(new Address(75, "Place de la mairie", "DIJON", "21000"),
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM2, teacher2, "Les longueurs", "45 minutes",
						LocalDateTime.of(2023, Month.APRIL, 12, 9, 45), 3, new BigDecimal("27")));
		physicalRepository.save(physical2);

		VirtualOption virtual3 = new VirtualOption("www.zoom.us", "Zoom",
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.CINQUIEME, teacher1, "Babylone", "1 heure",
						LocalDateTime.of(2022, Month.MARCH, 12, 14, 30), 5, new BigDecimal("25")));
		virtualRepository.save(virtual3);

		PhysicalOption physical3 = new PhysicalOption(new Address(13, "Rue de la fontaine", "LYON", "69009"),
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.QUATRIEME, teacher1,
						"L'urbanisation du monde", "1 heure et quart", LocalDateTime.of(2023, Month.JANUARY, 1, 10, 15),
						8, new BigDecimal("27")));
		physicalRepository.save(physical3);

		VirtualOption virtual4 = new VirtualOption("www.teams.fr", "Teams",
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.SECONDE, teacher6, "Les fonctions", "1 heure 30 minutes",
						LocalDateTime.of(2023, Month.MARCH, 30, 9, 30), 4, new BigDecimal("30")));
		virtualRepository.save(virtual3);

		PhysicalOption physical4 = new PhysicalOption(new Address(13, "Rue de la fontaine", "Valence", "26000"),
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.TERMINALE, teacher6,
						"Les matrices", "2 heures", LocalDateTime.of(2023, Month.APRIL, 1, 10, 15),
						3, new BigDecimal("35")));
		physicalRepository.save(physical3);

		// Learning Path
		LearningPath learningPath1 = new LearningPath(virtual3.getSynchronousLesson(), student1,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "", "");
		learningPathRepository.save(learningPath1);
		LearningPath learningPath2 = new LearningPath(virtual3.getSynchronousLesson(), student5,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "", "");
		learningPathRepository.save(learningPath2);
		LearningPath learningPath3 = new LearningPath(physical3.getSynchronousLesson(), student1,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45),
				"A l'écoute et pose des questions. Ce sont les clés pour progresser. Continue ainsi !", "");
		learningPathRepository.save(learningPath3);
		LearningPath learningPath4 = new LearningPath(physical3.getSynchronousLesson(), student5,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "A le soucis de bien faire. Il faut continuer ainsi.",
				"");
		learningPathRepository.save(learningPath4);

		// Creating some units
		Unit unit1 = new Unit("La Première Guerre mondiale",
				"En 1914, les grandes puissances appartiennent à des systèmes d'alliance antagonistes et les relations internationales sont tendues avec l'émergence de crises un peu partout : Balkans, politique coloniale, Weltpolitik de l'Allemagne.\r\n"
				+ "\r\n"
				+ "Après l'attentat de Sarajevo le 28 juin 1914, l'Autriche-Hongrie lance un ultimatum à la Serbie. Le refus des conditions sert de prétexte pour déclarer la guerre à la Serbie le 28 juillet. En une semaine, toute l'Europe entre en guerre par le jeu des alliances et par réflexe patriotique.",
				teacher1, AcademicLevel.TROISIEME);
		unit1.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit1);

		Unit unit2 = new Unit("Les nombres entiers",
				"Les nombres entiers sont des nombres qui ne comportent pas de partie décimale. Ils peuvent être positifs (plus grands que zéro), négatifs (plus petits que zéro) ou nuls..",
				teacher2, AcademicLevel.CM1);
		unit2.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit2);

		Unit unit3 = new Unit("Les éléments et composés chimiques",
				"Toutes les substances autour de nous sont constituées d'éléments ou de composés chimiques. Les éléments chimiques sont des substances simples qui ne peuvent pas être décomposées en d'autres substances par des moyens chimiques. Les composés chimiques sont des substances qui sont formées par la combinaison de deux ou plusieurs éléments chimiques différents.\r\n"
				+ "\r\n"
				+ "Les éléments chimiques sont représentés par des symboles chimiques, tels que H pour l'hydrogène, O pour l'oxygène et C pour le carbone. Il existe plus de 100 éléments différents dans la nature, mais seulement une trentaine d'entre eux sont couramment utilisés..",
				teacher3, AcademicLevel.QUATRIEME);
		unit3.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit3);

		Unit unit4 = new Unit("L'énergie",
				"L'énergie est une grandeur physique qui mesure la capacité d'un système à produire un travail. Elle est présente dans tous les phénomènes physiques et chimiques qui se produisent autour de nous. Il existe différents types d'énergie, tels que l'énergie cinétique, l'énergie potentielle, l'énergie électrique, l'énergie thermique, l'énergie lumineuse, etc.\r\n"
				+ "\r\n"
				+ "L'énergie cinétique est l'énergie liée au mouvement d'un objet. Plus un objet se déplace rapidement, plus son énergie cinétique est grande. L'énergie potentielle est l'énergie liée à la position d'un objet dans un champ de force, comme la gravité ou l'électricité. Plus un objet est haut ou chargé, plus son énergie potentielle est grande..",
				teacher3, AcademicLevel.CINQUIEME);
		unitRepository.save(unit4);

		Unit unit5 = new Unit("L'Union Européenne",
				"L'Union européenne est une organisation composée de 27 pays européens qui ont décidé de travailler ensemble pour se soutenir mutuellement et résoudre des problèmes communs. Elle a été créée en 1957 avec six pays fondateurs : l'Allemagne, la Belgique, la France, l'Italie, le Luxembourg et les Pays-Bas.\r\n"
				+ "\r\n"
				+ "Les membres de l'Union européenne partagent un certain nombre de choses en commun, comme la monnaie (l'euro) dans 19 pays, des règles communes sur l'emploi, l'environnement et la sécurité alimentaire. Ils travaillent également ensemble pour résoudre des problèmes tels que le changement climatique et l'immigration.\r\n"
				+ "\r\n"
				+ "L'Union européenne est dirigée par des responsables politiques élus qui travaillent à Bruxelles, en Belgique. Le Parlement européen est l'une des principales institutions de l'Union européenne, composé de députés élus par les citoyens européens.",
				teacher1, AcademicLevel.SIXIEME);
		unit5.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit5);

		Unit unit6 = new Unit("Théorème de Pythagore",
				"Le théorème de Pythagore s'applique uniquement aux triangles rectangles. \r\n" + "\r\n"
						+ "Un triangle est dit rectangle quand 2 côtés de celui-ci sont perpendiculaires entre eux : \r\n"
						+ "ils forment ainsi un angle droit de 90°. \r\n" + "\r\n"
						+ "Le théorème de Pythagore permet de trouver la mesure d’un côté lorsque l'on connait la mesure des deux autres côtés. \r\n"
						+ "\r\n" + "Selon le théorème de Pythagore : \r\n"
						+ "dans un triangle ABC rectangle en A, la somme des carrés des 2 côtés AB et AC situés de part et d'autre de l'angle droit est égale au carré de l'hypothénuse BC.\r\n"
						+ "",
				teacher4, AcademicLevel.QUATRIEME);
		unit6.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit6);

		Unit unit7 = new Unit("Equation du 1er degré à une inconnue",
				"Une équation est une expression mathématique qui contient une ou plusieurs inconnues et qui doit être résolue pour trouver la valeur de ces inconnues. Les équations du premier degré à une inconnue sont des équations de la forme ax + b = c, où a, b et c sont des nombres connus et x est l'inconnue que l'on cherche à trouver.\r\n"
				+ "\r\n"
				+ "Pour résoudre une équation du premier degré à une inconnue, on utilise des propriétés mathématiques qui nous permettent de déplacer les termes de l'équation d'un côté à l'autre de l'égalité sans la changer. Le but est de réussir à isoler l'inconnue x d'un côté de l'équation pour trouver sa valeur.", teacher4, AcademicLevel.QUATRIEME);
		unit7.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit7);

		Unit unit8 = new Unit("Système d'équation à deux inconnues",
				"Un système d'équations à deux inconnues est un ensemble de deux équations qui contiennent deux inconnues différentes, par exemple x et y, et qui doivent être résolues simultanément pour trouver les valeurs de x et y qui satisfont à ces deux équations.", teacher4, AcademicLevel.TROISIEME);
		unit8.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit8);

		Unit unit9 = new Unit("La morale et l'éthique",
				"La morale et l'éthique sont des branches de la philosophie qui étudient la question de la moralité et de la valeur. Les questions morales portent sur des sujets tels que ce qui est bien et mal, ce qui est juste et injuste, et les raisons pour lesquelles nous devrions agir de manière morale.",
				teacher5, AcademicLevel.TROISIEME);
		unit9.setSubject(SubjectEnum.PHILOSOPHIE);
		unitRepository.save(unit9);

		Unit unit10 = new Unit("Le Moyen-Age",
				"Le Moyen Âge est une période historique qui s'étend de la chute de l'Empire romain d'Occident en 476 jusqu'à la découverte de l'Amérique en 1492. Cette période est souvent divisée en trois grandes périodes : le Haut Moyen Âge (476-1000), le Moyen Âge central (1000-1300) et le Bas Moyen Âge (1300-1492).",
				teacher5, AcademicLevel.CINQUIEME);
		unitRepository.save(unit10);

		Unit unit11 = new Unit("La numération",
				"Les nombres sont composés de chiffres. Les chiffres utilisés en numération sont 0, 1, 2, 3, 4, 5, 6, 7, 8 et 9.\r\n"
				+ "\r\n"
				+ "Les nombres sont écrits à l'aide de chiffres placés dans des colonnes qui ont une valeur différente en fonction de leur position. Par exemple, dans le nombre 345, le chiffre 5 représente les unités, le chiffre 4 représente les dizaines et le chiffre 3 représente les centaines.\r\n"
				+ "\r\n"
				+ "Pour lire un nombre, il suffit de lire les chiffres en partant de la gauche et en respectant leur valeur de position. Par exemple, le nombre 345 se lit \"trois cent quarante-cinq\"..",
				teacher2, AcademicLevel.CE2);
		unit11.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit11);

		Unit unit12 = new Unit("L'atome",
				"Un atome est la plus petite unité de matière qui a les propriétés d'un élément chimique. Chaque élément chimique est défini par le nombre de protons dans le noyau de ses atomes. Le nombre de protons est appelé numéro atomique et il détermine l'identité de l'élément. Par exemple, tous les atomes d'oxygène ont huit protons dans leur noyau.\r\n"
				+ "\r\n"
				+ "Les atomes sont constitués de trois types de particules subatomiques : les protons, les neutrons et les électrons. Les protons ont une charge positive et les électrons ont une charge négative, tandis que les neutrons n'ont pas de charge. Les électrons orbitent autour du noyau des atomes et sont organisés en couches électroniques. Chaque couche électronique peut contenir un nombre maximum d'électrons.",
				teacher3, AcademicLevel.CINQUIEME);
		unitRepository.save(unit12);

		Unit unit13 = new Unit("L'anatomie",
				"Le corps humain est composé de plusieurs systèmes qui travaillent ensemble pour assurer le fonctionnement du corps. Les principaux systèmes du corps humain sont le système digestif, le système circulatoire, le système respiratoire, le système nerveux, le système musculaire, le système squelettique et le système endocrinien.\r\n"
				+ "\r\n"
				+ "Le système digestif est responsable de la digestion des aliments. Il se compose de l'œsophage, de l'estomac, de l'intestin grêle et du côlon. Les aliments sont décomposés en nutriments dans le système digestif et absorbés dans le sang pour être transportés à travers le corps..",
				teacher2, AcademicLevel.CM2);
		unit13.setStatus(UnitStatusEnum.VALIDATED);
		unit13.setSubject(SubjectEnum.BIOLOGIE);
		unitRepository.save(unit13);

	}

	private Student createStudent(School school, User user, AcademicLevel level) {
		Student student = new Student(level);
		student.setUser(user);
		student.setSchool(school);
		studentRepository.save(student);
		return student;
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
		teacher.setStatus(TeacherStatusEnum.Approved);
		teacherRepository.save(teacher);
		return teacher;
	}

}
