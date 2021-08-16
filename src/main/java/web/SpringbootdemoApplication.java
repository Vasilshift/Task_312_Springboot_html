package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringbootdemoApplication {
		//implements CommandLineRunner

//	private final RoleRepository roleRepository;
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	@Autowired
//	public SpringbootdemoApplication(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		this.roleRepository = roleRepository;
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
////		Role admin = new Role("ROLE_ADMIN");
////		Role user = new Role("ROLE_USER");
////		userRepository.save(admin);
////		roleRepository.save(user);
//
//		userRepository.save(new User("Василий", "Уткин", 49, "admin@mail.com",
//				passwordEncoder.encode("admin"), new Set<>() {	});
//		userRepository.save(new User("Дмитрий", "Губерниев", 46, "user@mail.com",
//				passwordEncoder.encode("user"),
//				new HashSet<>() {
//					add(user);
//				}));
//	}

}
