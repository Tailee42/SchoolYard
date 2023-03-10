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
				"Pour permettre ?? vos professeurs de proposer des cours particuliers");
		featureRepository.save(feature1);

		Feature feature2 = new Feature("Cours offline",
				"Pour permettre ?? vos professeurs de proposer du contenu p??dagogique en ligne");
		featureRepository.save(feature2);

		Feature feature3 = new Feature("Outil de paiement",
				"Pour permettre ?? vos adh??rents de r??gler leurs achats sur la plateforme");
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

		Membership membership1 = new Membership(LocalDate.of(2024, Month.JANUARY, 5),
				LocalDate.of(2023, Month.JANUARY, 5), subscription1);
		membershipRepository.save(membership1);

		Membership membership2 = new Membership(LocalDate.of(2023, Month.MARCH, 9),
				LocalDate.of(2022, Month.MARCH, 9), subscription2);
		membershipRepository.save(membership2);

		Membership membership3 = new Membership(LocalDate.of(2023, Month.NOVEMBER, 12),
				LocalDate.of(2022, Month.NOVEMBER, 12), subscription3);
		membershipRepository.save(membership3);

		Membership membership4 = new Membership(LocalDate.of(2023, Month.AUGUST, 10),
				LocalDate.of(2024, Month.AUGUST, 10), subscription3);
		membershipRepository.save(membership4);

		Membership membership5 = new Membership(LocalDate.of(2023, Month.JUNE, 8),
				LocalDate.of(2022, Month.JUNE, 8), subscription1);
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
						"07 48 75 24 48", new Address(52, "Place du march?? au bois", "TOURS-EN-SAVOIE", "73290"))));
		userRepository.save(user4);

		User user5 = new User("beatrice", LocalDate.of(2023, Month.FEBRUARY, 07), RoleTypeEnum.USER,
				new Security("Beatrice-123"), new Profil("DUPONT", "Beatrice", "", new Contact("beatrice@gmail.com",
						"07 58 04 48 80", new Address(141, "Place d'Italie", "VILLEFRANCE-SUR-MER", "06230"))));
		userRepository.save(user5);

		// Schools need a membership !

		School school1 = new School("Coll??ge des bois", "Logo_Crop_CollegeDesBois.png",
				"Le coll??ge o?? les connaissances prennent racine", new ArrayList<>(),
				new Contact("collegeDesBois@gmail.com", "05 05 05 05 05",
						new Address(789, "Rue du college", "TOULOUSE", "31000")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage1 = new SchoolPage(
				"Le Coll??ge des Bois est un ??tablissement d'enseignement r??put?? qui offre une ??ducation de qualit?? ?? tous les ??l??ves. Avec un corps enseignant exp??riment?? et des installations modernes, le coll??ge s'efforce de fournir un environnement ??ducatif stimulant qui encourage les ??l??ves ?? se d??velopper acad??miquement et personnellement. Le slogan du coll??ge, \"Le coll??ge o?? les connaissances prennent racine\", illustre la conviction que l'??ducation est un processus continu qui n??cessite une base solide pour se d??velopper. En offrant un enseignement de qualit?? qui prend en compte les besoins individuels de chaque ??l??ve, Le Coll??ge des Bois aide les ??l??ves ?? d??velopper les comp??tences n??cessaires pour r??ussir dans leur vie future.",
				new SchoolValue("Le coll??ge o?? les connaissances prennent racine",
						"Favoriser la confiance en soi, l???autonomie, tout en permettant ?? l???enfant d?????voluer ?? son propre rythme et en toute libert??.",
						"PhotoCollegeDesBois.png"),
				new Theme("D4C685", "F7EF81", "E5F1EB", FontEnum.ROBOTO.name()));
		school1.setSchoolPage(schoolPage1);
		school1.setStatusSchool(StatusSchool.PUBLISHED);
		school1.setMembership(membership3);
		schoolRepository.save(school1);
		createAdmin(user1, school1);

		School school2 = new School("Ecole de la plage", "Logo_Crop_EcoleDeLaPlage.png",
				"Se d??tendre pour mieux apprendre", new ArrayList<>(), new Contact("ecoledelaplage@gmail.com",
						"05 36 36 36 36", new Address(56, "Rue de la plage", "BIARRITZ", "64200")),
				SchoolTypeEnum.ELEMENTAIRE);
		SchoolPage schoolPage2 = new SchoolPage(
				"L'Ecole de la Plage est un ??tablissement ??ducatif unique en son genre, offrant aux ??l??ves la possibilit?? d'apprendre tout en profitant du cadre enchanteur de la plage. Avec des enseignants passionn??s et des programmes p??dagogiques innovants, l'??cole encourage les ??l??ves ?? explorer leur curiosit??, ?? d??velopper leurs comp??tences et ?? s'??panouir tout en profitant de la d??tente que procure la vue de la mer. L'??cole offre une exp??rience ??ducative compl??te, int??grant une vari??t?? d'activit??s extrascolaires pour encourager le d??veloppement personnel et social. Que ce soit pour une journ??e, une semaine ou une ann??e scolaire, l'Ecole de la Plage est l'endroit id??al pour apprendre, se d??tendre et s'amuser dans un environnement inspirant et stimulant.",
				new SchoolValue("Se d??tendre pour mieux apprendre",
						"Favoriser l'apprentissage et l'esprit d'??quipe par le jeu en plein air.", "PhotoEcoleDeLaPlage.png"),
				new Theme("FECDAA", "F5F58E", "F8FFF4", FontEnum.UBUNTU.name()));
		school2.setSchoolPage(schoolPage2);
		school2.setStatusSchool(StatusSchool.PUBLISHED);
		school2.setMembership(membership3);
		schoolRepository.save(school2);
		createAdmin(user2, school2);

		School school3 = new School("Lyc??e Beaux B??tons", "LogoLyceeBeauxBatons.png", "Lyc??e priv??", new ArrayList<>(),
				new Contact("lyceebeauxbatons@gmail.com", "02 86 11 23 36",
						new Address(11, "Avenue des lutins", "PAIMPONT", "35380")),
				SchoolTypeEnum.LYCEE);
		SchoolPage schoolPage3 = new SchoolPage("Le lyc??e Beaux B??tons est un ??tablissement formant les jeunes ??l??ves ?? l'art et ?? la pratique de l'apprentissage gr??ce aux cours particuliers exceptionnels de ses enseignants.", new SchoolValue("Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.","PhotoLyceeBeauxBatons.png" ,
				"PhotoLyceeBeauxBatons.png"), new Theme("6CA7E6", "A6A9AB", "F9F8F8", FontEnum.UBUNTU.name()));
		school3.setSchoolPage(schoolPage3);
		school3.setStatusSchool(StatusSchool.PUBLISHED);
		school3.setMembership(membership2);
		schoolRepository.save(school3);
		createAdmin(user3, school3);

		School school4 = new School("Coll??ge de la montagne", "Logo_Crop_CollegeDeLaMontagneNoName.png",
				"Hissons nos ambitions vers les sommets", new ArrayList<>(),
				new Contact("collegeDeLaMontagne@gmail.com", "04 06 06 06 06",
						new Address(45, "Rue du sommet", "ALBERTVILLE", "73200")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage4 = new SchoolPage(
				"Le Coll??ge de la Montagne est une institution ??ducative reconnue pour sa qualit?? acad??mique et sa vocation ?? former les leaders de demain. Situ?? dans un environnement naturel exceptionnel, au pied des montagnes, l'??tablissement offre ?? ses ??l??ves un cadre d'apprentissage stimulant et inspirant. Les enseignants, exp??riment??s et passionn??s, dispensent des programmes p??dagogiques novateurs et adapt??s aux besoins de chaque ??l??ve, dans un environnement propice ?? l'excellence acad??mique. Le Coll??ge de la Montagne est ??galement un lieu de vie, o?? les ??l??ves peuvent d??velopper leur personnalit?? et leur cr??ativit?? gr??ce ?? un large ??ventail d'activit??s extrascolaires, sportives et culturelles. L'??tablissement est ainsi un lieu d'??panouissement, o?? les ??l??ves apprennent ?? se d??passer et ?? atteindre les sommets de leur potentiel.",
				new SchoolValue("Le partage en plein air",
						"Favoriser le partage, le respect de l'autre et l'ouverture d'esprit.", "PhotoCollegeDeLaMontagne.png"),
				new Theme("e2daf5", "5eebb3", "f5faf6", FontEnum.PLAYFAIRDISPLAY.name()));
		school4.setSchoolPage(schoolPage4);
		school4.setStatusSchool(StatusSchool.PUBLISHED);
		school4.setMembership(membership5);
		schoolRepository.save(school4);
		createAdmin(user4, school4);

		School school5 = new School("Ecole du Port", "LogoEcoleDuPort.png",
				"Ecole o?? l'on vogue ensemble vers une meilleure connaissance", new ArrayList<>(),
				new Contact("ecoleDuPort@gmail.com", "04 03 03 03 03",
						new Address(17, "Rue de l'embarcad??re", "NICE", "06100")),
				SchoolTypeEnum.ELEMENTAIRE);
		SchoolPage schoolPage5 = new SchoolPage("Aucune crainte, avec nous, votre r??ussite scolaire est assur??e ! L'air marin procure des effets spectaculaires sur la m??moire et la rapidit?? de r??alisation de ses devoirs ! ",
				new SchoolValue("Ecole o?? l'on vogue ensemble vers une meilleure connaissance", "Parvenir ?? trouver en ??quipe la bonne voie pour participer et progresser ?? son rythme dans l'apprentissage des savoirs.", "PhotoEcoleDuPort.png"),
				new Theme("c8f5fa", "21c493", "f7f3ed", FontEnum.ZEYADA.name()));
		school5.setSchoolPage(schoolPage5);
		school5.setStatusSchool(StatusSchool.PUBLISHED);
		school5.setMembership(membership4);
		schoolRepository.save(school5);
		createAdmin(user5, school5);
		
		School school6 = new School("Coll??ge Poudlard", NO_LOGO_PNG,
				"Ne chatouillez jamais un dragon qui dort...", new ArrayList<>(),
				new Contact("albus@ac-creteil.com", "01 01 19 35 12",
						new Address(4, "privet drive", "FOUGERES", "35300")),
				SchoolTypeEnum.COLLEGE);
		SchoolPage schoolPage6 = new SchoolPage("Ne chatouillez jamais un dragon qui dort...",
				new SchoolValue("","", "empty_school_picture.png"),
				new Theme("c8f5fa", "21c493", "f7f3ed", FontEnum.ZEYADA.name()));
		school6.setSchoolPage(schoolPage6);
		school6.setStatusSchool(StatusSchool.TOPUBLISH);
		school6.setMembership(membership4);
		schoolRepository.save(school6);
		createAdmin(user5, school6);

		// Create User for student (from user11 to user19 for future)

		User user11 = new User("louis", LocalDate.of(2022, Month.NOVEMBER, 17), RoleTypeEnum.USER,
				new Security("Louis-123"), new Profil("MARCHAND", "Louis", "", new Contact("louis@gmail.com",
						"06 96 69 69 69", new Address(3, "Rue de la gare", "VIERZON", "18100"))));
		userRepository.save(user11);
		Student student1 = createStudent(school1, user11, AcademicLevel.QUATRIEME);
		userRepository.save(user11);
		Student student2 = createStudent(school3, user11, AcademicLevel.SECONDE);

		User user12 = new User("fleur", LocalDate.of(2022, Month.OCTOBER, 25), RoleTypeEnum.USER,
				new Security("Fleur-123"), new Profil("ALBRAND", "Fleur", "", new Contact("fleur@gmail.com",
						"06 41 14 14 14", new Address(41, "Rue des escaliers", "ORLEANS", "45000"))));
		userRepository.save(user12);
		Student student3 = createStudent(school2, user12, AcademicLevel.CM1);

		User user13 = new User("herve", LocalDate.of(2022, Month.SEPTEMBER, 5), RoleTypeEnum.USER,
				new Security("Herve-123"), new Profil("LEGRAND", "Herv??", "", new Contact("herve@gmail.com",
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
						"06 62 62 62 62", new Address(74, "Rue du t??l??ph??rique", "BORDEAUX", "33000"))));
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
						"Seigneurs et paysans au Moyen ??ge", "1 heure", LocalDateTime.of(2023, Month.MARCH, 12, 14, 30),
						5, new BigDecimal("25")));
		virtualRepository.save(virtual1);

		PhysicalOption physical1 = new PhysicalOption(new Address(13, "Rue de la fontaine", "LYON", "69009"),
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.TROISIEME, teacher1, "La guerre froide",
						"1 heure et quart", LocalDateTime.of(2023, Month.APRIL, 1, 10, 15), 8, new BigDecimal("27")));
		physicalRepository.save(physical1);

		VirtualOption virtual2 = new VirtualOption("www.zoom.us", "Zoom",
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM1, teacher2, "Les nombres d??cimaux",
						"1 heure et demi", LocalDateTime.of(2023, Month.MARCH, 24, 10, 30), 3, new BigDecimal("30")));
		virtualRepository.save(virtual2);

		PhysicalOption physical2 = new PhysicalOption(new Address(75, "Place de la mairie", "DIJON", "21000"),
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.CM2, teacher2, "Les longueurs", "45 minutes",
						LocalDateTime.of(2023, Month.APRIL, 12, 9, 45), 3, new BigDecimal("27")));
		physicalRepository.save(physical2);

		VirtualOption virtual3 = new VirtualOption("www.zoom.us", "Zoom",
				new SynchronousLesson(SubjectEnum.HISTOIRE, AcademicLevel.QUATRIEME, teacher1, "Babylone", "1 heure",
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
		virtualRepository.save(virtual4);

		PhysicalOption physical4 = new PhysicalOption(new Address(13, "Rue de la fontaine", "Valence", "26000"),
				new SynchronousLesson(SubjectEnum.MATHS, AcademicLevel.TERMINALE, teacher6,
						"Les matrices", "2 heures", LocalDateTime.of(2023, Month.APRIL, 1, 10, 15),
						3, new BigDecimal("35")));
		physicalRepository.save(physical4);

		// Learning Path
		LearningPath learningPath1 = new LearningPath(virtual3.getSynchronousLesson(), student1,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "", "");
		learningPathRepository.save(learningPath1);
		LearningPath learningPath2 = new LearningPath(virtual3.getSynchronousLesson(), student5,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "", "");
		learningPathRepository.save(learningPath2);
		LearningPath learningPath3 = new LearningPath(physical3.getSynchronousLesson(), student1,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45),
				"A l'??coute et pose des questions. Ce sont les cl??s pour progresser. Continue ainsi !", "");
		learningPathRepository.save(learningPath3);
		LearningPath learningPath4 = new LearningPath(physical3.getSynchronousLesson(), student5,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "A le soucis de bien faire. Il faut continuer ainsi.",
				"");
		learningPathRepository.save(learningPath4);
		LearningPath learningPath5 = new LearningPath(virtual4.getSynchronousLesson(), student2,
				LocalDateTime.of(2022, Month.APRIL, 12, 9, 45), "A le soucis de bien faire. Il faut continuer ainsi.",
				"");
		learningPathRepository.save(learningPath5);

		// Creating some units
		Unit unit1 = new Unit("La Premi??re Guerre mondiale",
				"En 1914, les grandes puissances appartiennent ?? des syst??mes d'alliance antagonistes et les relations internationales sont tendues avec l'??mergence de crises un peu partout : Balkans, politique coloniale, Weltpolitik de l'Allemagne.\r\n"
				+ "\r\n"
				+ "Apr??s l'attentat de Sarajevo le 28 juin 1914, l'Autriche-Hongrie lance un ultimatum ?? la Serbie. Le refus des conditions sert de pr??texte pour d??clarer la guerre ?? la Serbie le 28 juillet. En une semaine, toute l'Europe entre en guerre par le jeu des alliances et par r??flexe patriotique.",
				teacher1, AcademicLevel.TROISIEME);
		unit1.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit1);

		Unit unit2 = new Unit("Les nombres entiers",
				"Les nombres entiers sont des nombres qui ne comportent pas de partie d??cimale. Ils peuvent ??tre positifs (plus grands que z??ro), n??gatifs (plus petits que z??ro) ou nuls..",
				teacher2, AcademicLevel.CM1);
		unit2.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit2);

		Unit unit3 = new Unit("Les ??l??ments et compos??s chimiques",
				"Toutes les substances autour de nous sont constitu??es d'??l??ments ou de compos??s chimiques. Les ??l??ments chimiques sont des substances simples qui ne peuvent pas ??tre d??compos??es en d'autres substances par des moyens chimiques. Les compos??s chimiques sont des substances qui sont form??es par la combinaison de deux ou plusieurs ??l??ments chimiques diff??rents.\r\n"
				+ "\r\n"
				+ "Les ??l??ments chimiques sont repr??sent??s par des symboles chimiques, tels que H pour l'hydrog??ne, O pour l'oxyg??ne et C pour le carbone. Il existe plus de 100 ??l??ments diff??rents dans la nature, mais seulement une trentaine d'entre eux sont couramment utilis??s..",
				teacher3, AcademicLevel.QUATRIEME);
		unit3.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit3);

		Unit unit4 = new Unit("L'??nergie",
				"L'??nergie est une grandeur physique qui mesure la capacit?? d'un syst??me ?? produire un travail. Elle est pr??sente dans tous les ph??nom??nes physiques et chimiques qui se produisent autour de nous. Il existe diff??rents types d'??nergie, tels que l'??nergie cin??tique, l'??nergie potentielle, l'??nergie ??lectrique, l'??nergie thermique, l'??nergie lumineuse, etc.\r\n"
				+ "\r\n"
				+ "L'??nergie cin??tique est l'??nergie li??e au mouvement d'un objet. Plus un objet se d??place rapidement, plus son ??nergie cin??tique est grande. L'??nergie potentielle est l'??nergie li??e ?? la position d'un objet dans un champ de force, comme la gravit?? ou l'??lectricit??. Plus un objet est haut ou charg??, plus son ??nergie potentielle est grande..",
				teacher3, AcademicLevel.CINQUIEME);
		unitRepository.save(unit4);

		Unit unit5 = new Unit("L'Union Europ??enne",
				"L'Union europ??enne est une organisation compos??e de 27 pays europ??ens qui ont d??cid?? de travailler ensemble pour se soutenir mutuellement et r??soudre des probl??mes communs. Elle a ??t?? cr????e en 1957 avec six pays fondateurs : l'Allemagne, la Belgique, la France, l'Italie, le Luxembourg et les Pays-Bas.\r\n"
				+ "\r\n"
				+ "Les membres de l'Union europ??enne partagent un certain nombre de choses en commun, comme la monnaie (l'euro) dans 19 pays, des r??gles communes sur l'emploi, l'environnement et la s??curit?? alimentaire. Ils travaillent ??galement ensemble pour r??soudre des probl??mes tels que le changement climatique et l'immigration.\r\n"
				+ "\r\n"
				+ "L'Union europ??enne est dirig??e par des responsables politiques ??lus qui travaillent ?? Bruxelles, en Belgique. Le Parlement europ??en est l'une des principales institutions de l'Union europ??enne, compos?? de d??put??s ??lus par les citoyens europ??ens.",
				teacher1, AcademicLevel.SIXIEME);
		unit5.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit5);

		Unit unit6 = new Unit("Th??or??me de Pythagore",
				"Le th??or??me de Pythagore s'applique uniquement aux triangles rectangles. \r\n" + "\r\n"
						+ "Un triangle est dit rectangle quand 2 c??t??s de celui-ci sont perpendiculaires entre eux : \r\n"
						+ "ils forment ainsi un angle droit de 90??. \r\n" + "\r\n"
						+ "Le th??or??me de Pythagore permet de trouver la mesure d???un c??t?? lorsque l'on connait la mesure des deux autres c??t??s. \r\n"
						+ "\r\n" + "Selon le th??or??me de Pythagore : \r\n"
						+ "dans un triangle ABC rectangle en A, la somme des carr??s des 2 c??t??s AB et AC situ??s de part et d'autre de l'angle droit est ??gale au carr?? de l'hypoth??nuse BC.\r\n"
						+ "",
				teacher4, AcademicLevel.QUATRIEME);
		unit6.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit6);

		Unit unit7 = new Unit("Equation du 1er degr?? ?? une inconnue",
				"Une ??quation est une expression math??matique qui contient une ou plusieurs inconnues et qui doit ??tre r??solue pour trouver la valeur de ces inconnues. Les ??quations du premier degr?? ?? une inconnue sont des ??quations de la forme ax + b = c, o?? a, b et c sont des nombres connus et x est l'inconnue que l'on cherche ?? trouver.\r\n"
				+ "\r\n"
				+ "Pour r??soudre une ??quation du premier degr?? ?? une inconnue, on utilise des propri??t??s math??matiques qui nous permettent de d??placer les termes de l'??quation d'un c??t?? ?? l'autre de l'??galit?? sans la changer. Le but est de r??ussir ?? isoler l'inconnue x d'un c??t?? de l'??quation pour trouver sa valeur.", teacher4, AcademicLevel.QUATRIEME);
		unit7.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit7);

		Unit unit8 = new Unit("Syst??me d'??quation ?? deux inconnues",
				"Un syst??me d'??quations ?? deux inconnues est un ensemble de deux ??quations qui contiennent deux inconnues diff??rentes, par exemple x et y, et qui doivent ??tre r??solues simultan??ment pour trouver les valeurs de x et y qui satisfont ?? ces deux ??quations.", teacher4, AcademicLevel.TROISIEME);
		unit8.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit8);

		Unit unit9 = new Unit("La morale et l'??thique",
				"La morale et l'??thique sont des branches de la philosophie qui ??tudient la question de la moralit?? et de la valeur. Les questions morales portent sur des sujets tels que ce qui est bien et mal, ce qui est juste et injuste, et les raisons pour lesquelles nous devrions agir de mani??re morale.",
				teacher5, AcademicLevel.TROISIEME);
		unit9.setSubject(SubjectEnum.PHILOSOPHIE);
		unitRepository.save(unit9);

		Unit unit10 = new Unit("Le Moyen-Age",
				"Le Moyen ??ge est une p??riode historique qui s'??tend de la chute de l'Empire romain d'Occident en 476 jusqu'?? la d??couverte de l'Am??rique en 1492. Cette p??riode est souvent divis??e en trois grandes p??riodes : le Haut Moyen ??ge (476-1000), le Moyen ??ge central (1000-1300) et le Bas Moyen ??ge (1300-1492).",
				teacher5, AcademicLevel.CINQUIEME);
		unitRepository.save(unit10);

		Unit unit11 = new Unit("La num??ration",
				"Les nombres sont compos??s de chiffres. Les chiffres utilis??s en num??ration sont 0, 1, 2, 3, 4, 5, 6, 7, 8 et 9.\r\n"
				+ "\r\n"
				+ "Les nombres sont ??crits ?? l'aide de chiffres plac??s dans des colonnes qui ont une valeur diff??rente en fonction de leur position. Par exemple, dans le nombre 345, le chiffre 5 repr??sente les unit??s, le chiffre 4 repr??sente les dizaines et le chiffre 3 repr??sente les centaines.\r\n"
				+ "\r\n"
				+ "Pour lire un nombre, il suffit de lire les chiffres en partant de la gauche et en respectant leur valeur de position. Par exemple, le nombre 345 se lit \"trois cent quarante-cinq\"..",
				teacher2, AcademicLevel.CE2);
		unit11.setStatus(UnitStatusEnum.VALIDATED);
		unitRepository.save(unit11);

		Unit unit12 = new Unit("L'atome",
				"Un atome est la plus petite unit?? de mati??re qui a les propri??t??s d'un ??l??ment chimique. Chaque ??l??ment chimique est d??fini par le nombre de protons dans le noyau de ses atomes. Le nombre de protons est appel?? num??ro atomique et il d??termine l'identit?? de l'??l??ment. Par exemple, tous les atomes d'oxyg??ne ont huit protons dans leur noyau.\r\n"
				+ "\r\n"
				+ "Les atomes sont constitu??s de trois types de particules subatomiques : les protons, les neutrons et les ??lectrons. Les protons ont une charge positive et les ??lectrons ont une charge n??gative, tandis que les neutrons n'ont pas de charge. Les ??lectrons orbitent autour du noyau des atomes et sont organis??s en couches ??lectroniques. Chaque couche ??lectronique peut contenir un nombre maximum d'??lectrons.",
				teacher3, AcademicLevel.CINQUIEME);
		unitRepository.save(unit12);

		Unit unit13 = new Unit("L'anatomie",
				"Le corps humain est compos?? de plusieurs syst??mes qui travaillent ensemble pour assurer le fonctionnement du corps. Les principaux syst??mes du corps humain sont le syst??me digestif, le syst??me circulatoire, le syst??me respiratoire, le syst??me nerveux, le syst??me musculaire, le syst??me squelettique et le syst??me endocrinien.\r\n"
				+ "\r\n"
				+ "Le syst??me digestif est responsable de la digestion des aliments. Il se compose de l'??sophage, de l'estomac, de l'intestin gr??le et du c??lon. Les aliments sont d??compos??s en nutriments dans le syst??me digestif et absorb??s dans le sang pour ??tre transport??s ?? travers le corps..",
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
