package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class DBInititialImpl implements DBInitial {

     private final RoleRepository roleRepository;
     private final UserServiceImpl userService;
     private final BCryptPasswordEncoder bcryptpasswordEncoder;

    @Autowired
    public DBInititialImpl(RoleRepository roleRepository, UserServiceImpl userService, BCryptPasswordEncoder bcryptpasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.bcryptpasswordEncoder = bcryptpasswordEncoder;
    }

    @PostConstruct
    @Override
    public void init() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(bcryptpasswordEncoder.encode("admin"));
        user1.setRoles(Collections.singleton(roleRepository.findRoleByName("ROLE_ADMIN")));
        userService.saveUser(user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(bcryptpasswordEncoder.encode("user"));
        user2.setRoles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER")));
        userService.saveUser(user2);
    }
}
