package fr.isika.cda.beans;

import fr.isika.cda.entities.AcademicLevel;
import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.student.Student;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SchoolRepository;
import fr.isika.cda.repositories.StudentRepository;
import fr.isika.cda.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;


@Startup
@Singleton
public class DataSetBean {
    @Inject
    private UserRepository userRepository;
    @Inject
    private MemberRepository memberRepository;
    @Inject
    private SchoolRepository schoolRepository;
    @Inject
    private StudentRepository studentRepository;

    @PostConstruct
    private void initBDD() {
        // Create User for admin of school (from user1 to user9)
        User user1 = new User("Albert",
                LocalDateTime.of(2023,
                        Month.JANUARY, 20, 19, 30, 40),
                RoleTypeEnum.USER,
                new Security("123"),
                new Profil("DUPOND",
                        "Albert",
                        "",
                        new Contact("albert@gmail.com",
                                "06 06 06 06 06",
                                new Address(12, "Rue du phare", "BREST", "29000"))));
        userRepository.save(user1);

        User user2 = new User("Emma",
                LocalDateTime.of(2022,
                        Month.DECEMBER, 05, 12, 20, 00),
                RoleTypeEnum.USER,
                new Security("aze"),
                new Profil("ROUX",
                        "Emma",
                        "",
                        new Contact("emma@gmail.com",
                                "06 78 78 78 78",
                                new Address(952, "Avenue de Paris", "ORLEANS", "45000"))));
        userRepository.save(user2);

        User user3 = new User("Bertrand",
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
        
        User user4 = new User("Florence",
                LocalDateTime.of(2022,
                        Month.SEPTEMBER, 01, 11, 11, 11),
                RoleTypeEnum.USER,
                new Security("lrc"),
                new Profil("PIAZELLI",
                        "Florence",
                        "",
                        new Contact("florence@gmail.com",
                                "07 48 48 48 48",
                                new Address(52, "Place d'Italie", "NICE", "06000"))));
        userRepository.save(user4);

        
        
        // Create School and admin
        School school1 = new School("Collège des bois",
                "",
                "Collège bienveillant et inclusif",
                new ArrayList<>(),
                new Contact("collegeDesBois@gmail.com",
                        "05 05 05 05 05",
                        new Address(789, "Rue du collège", "TOULOUSE", "31000")),
                SchoolTypeEnum.COLLEGE);
        schoolRepository.save(school1);
        createAdmin(user1, school1);


        School school2 = new School("Ecole de la plage",
                "",
                "Ecole en plein air",
                new ArrayList<>(),
                new Contact("ecoleDeLaplage@gmail.com",
                        "05 36 36 36 36",
                        new Address(56, "Rue de la plage", "BIARRITZ", "64200")),
                SchoolTypeEnum.ELEMENTAIRE);
        schoolRepository.save(school2);
        createAdmin(user2, school2);

        School school3 = new School("Collège de la montagne",
                "",
                "Collège pour aller plus loin et plus haut",
                new ArrayList<>(),
                new Contact("collegeDeLaMontagne@gmail.com",
                        "04 06 06 06 06",
                        new Address(45, "Rue du sommet", "ALBERTVILLE", "73200")),
                SchoolTypeEnum.COLLEGE);
        schoolRepository.save(school3);
        createAdmin(user3, school3);
        
        School school4 = new School("Ecole du Port",
                "",
                "Ecole où l'on vogue ensemble vers une meilleure connaissance",
                new ArrayList<>(),
                new Contact("ecoleDuPort@gmail.com",
                        "04 03 03 03 03",
                        new Address(17, "Rue de l'embarcadère", "NICE", "06100")),
                SchoolTypeEnum.ELEMENTAIRE);
        schoolRepository.save(school4);
        createAdmin(user4, school4);
        
        // Create User for student (from user11 to user19)
        User user11 = new User("Louis",
                LocalDateTime.of(2022,
                        Month.NOVEMBER, 17, 13, 25, 0),
                RoleTypeEnum.USER,
                new Security("456"),
                new Profil("MARCHAND",
                        "Louis",
                        "",
                        new Contact("louis@gmail.com",
                                "06 96 69 69 69",
                                new Address(3, "Rue de la gare", "VIERZON", "18100"))));
        userRepository.save(user11);
        createStudent(school1, user11, AcademicLevel.QUATRIEME);
        userRepository.save(user11);
        createStudent(school3, user11, AcademicLevel.TROISIEME);

        User user12 = new User("Fleur",
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
        
        User user13 = new User("Hervé",
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

        User user14 = new User("Emilie",
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
    }

    private void createStudent(School school1, User user11, AcademicLevel level) {
        Student student = new Student(level);
        student.setUser(user11);
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
