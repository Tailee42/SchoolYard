package fr.isika.cda.beans;

import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleType;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Startup
@Singleton
public class DataSetBean {
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    private void initBDD() {
//        userRepository.save(new User("albert",
//                new LocalDateTime(2023-01-20,12-22-00),
//                RoleType.USER,
//                new Security("123"),
//                new Profil("Dupond",
//                        "Albert",
//                        "",
//                        new Contact("albert@gmail.com",
//                                "0606060606",
//                                new Address(12, "rue du phare", "Brest", "29000")))));
    }
}
