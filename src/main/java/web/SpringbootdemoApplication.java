package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.model.Role;
import web.model.User;
import web.service.RoleServiceImpl;
import web.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	private UserServiceImpl userService;
	private RoleServiceImpl roleService;
	private BCryptPasswordEncoder bcryptpasswordEncoder;

	@Autowired
	public SpringbootdemoApplication(UserServiceImpl userService, RoleServiceImpl roleService, BCryptPasswordEncoder bcryptpasswordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.bcryptpasswordEncoder = bcryptpasswordEncoder;
	}

	@PostConstruct
	void init(){

		Role roleAdmin = new Role();
		roleAdmin.setId(1L);
		roleAdmin.setName("ROLE_ADMIN");
		roleService.save(roleAdmin);

		Role roleUser = new Role();
		roleUser.setId(2L);
		roleUser.setName("ROLE_USER");
		roleService.save(roleUser);

		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(bcryptpasswordEncoder.encode("admin"));
		user1.setLastname("adminov");
		user1.setAge("34");
		user1.setEmail("admin@mail.ru");
		user1.setRoles(Collections.singleton(roleService.findRoleByName("ROLE_ADMIN")));
		userService.saveUser(user1);

		User user2 = new User();
		user2.setUsername("user");
		user2.setPassword(bcryptpasswordEncoder.encode("user"));
		user2.setLastname("userov");
		user2.setAge("34");
		user2.setEmail("user@mail.ru");
		user2.setRoles(Collections.singleton(roleService.findRoleByName("ROLE_USER")));
		userService.saveUser(user2);
	}
}
