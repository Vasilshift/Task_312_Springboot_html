package web.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
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
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User findById(Long id){
        return userRepository.getOne(id);
    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


}
