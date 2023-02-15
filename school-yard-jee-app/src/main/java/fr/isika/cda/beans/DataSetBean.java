package fr.isika.cda.beans;

import fr.isika.cda.entities.SchoolType;
import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleType;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.school.Admin;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.SchoolRepository;
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

    @PostConstruct
    private void initBDD() {
        User user1 = new User("albert",
                LocalDateTime.of(2023,
                        Month.JANUARY, 20, 19, 30, 40),
                RoleType.USER,
                new Security("123"),
                new Profil("Dupond",
                        "Albert",
                        "",
                        new Contact("albert@gmail.com",
                                "0606060606",
                                new Address(12, "rue du phare", "Brest", "29000"))));
        userRepository.save(user1);

        User user2 = new User("emma",
                LocalDateTime.of(2022,
                        Month.DECEMBER, 05, 12, 20, 00),
                RoleType.USER,
                new Security("aze"),
                new Profil("Roux",
                        "Emma",
                        "",
                        new Contact("emma@gmail.com",
                                "0678787878",
                                new Address(952, "avenue de Paris", "Orléans", "45000"))));
        userRepository.save(user2);

        School school1 = new School("Collège des bois",
                "",
                "Collège bienviellant et inclusif",
                new ArrayList<>(),
                new Contact("collegeDesBois@gmail.com",
                        "0405050505",
                        new Address(789, "rue du college", "Toulouse", "31000")),
                SchoolType.COLLEGE);
        schoolRepository.save(school1);

        Admin admin1 = new Admin();
        admin1.setSchool(school1);
        admin1.setUser(user1);
        memberRepository.save(admin1);


        School school2 = new School("école de la plage",
                "",
                "école en plein air",
                new ArrayList<>(),
                new Contact("écoledelaplage@gmail.com",
                        "0236363636",
                        new Address(56, "rue de la plage", "Biarritz", "64200")),
                SchoolType.ELEMENTAIRE);
        schoolRepository.save(school2);

        Admin admin2 = new Admin();
        admin2.setSchool(school2);
        admin2.setUser(user2);
        memberRepository.save(admin2);
    }
}
