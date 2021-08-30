package web.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User findById(Long id){
        return userRepository.getOne(id);
    }

    @Override
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
