package fr.isika.cda.beans;

import java.time.LocalDate;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;
import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.Security;
import fr.isika.cda.entities.users.Profil;
import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.UserRepository;

@ManagedBean
public class CreateUserBean {

	private User user = new User();

	@Inject
	private UserRepository userRepository;

	public String create() {
		user.setLastConnection(LocalDate.now());
		user.setRole(RoleTypeEnum.USER);
		userRepository.save(user);
		user = new User();
		return "index?faces-redirect=true";
	}

	public void autofill() {
		user = new User("tiphaine", LocalDate.now(), RoleTypeEnum.USER, new Security("Tiphaine-123"),
				new Profil("Tiphaine", "Lalonde", "",
						new Contact("tiphaine@gmail.com", "06 07 08 09 10",
								new Address(8, "rue des Fleurs", "Chartres", "28000"))));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
