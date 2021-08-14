package web.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

//    public void addRolesToUser(User user, Set<Role> roleView) {
//        Set<Role> roleList = new HashSet<>();
//        for (String role : roleView) {
//            if (role.equals("ROLE_ADMIN")) {
//                roleList.add(roleRepository.findRoleByName("ROLE_ADMIN"));
//            } else if (role.equals("ROLE_USER")) {
//                roleList.add(roleRepository.findRoleByName("ROLE_USER"));
//            }
//        }
//        user.setRoles(roleList);
//    }

    public void addRolesToUser(User user, Role roleView) {
        Set<Role> roleList = new HashSet<>();
            if (roleView.equals("ROLE_ADMIN")) {
                roleList.add(roleRepository.findRoleByName("ROLE_ADMIN"));
            } else if (roleView.equals("ROLE_USER")) {
                roleList.add(roleRepository.findRoleByName("ROLE_USER"));
            }
        user.setRoles(roleList);
    }




}
