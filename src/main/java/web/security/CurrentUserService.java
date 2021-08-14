package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;
import web.repository.UserRepository;
import web.service.UserService;

@Service
public class CurrentUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    public CurrentUserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new CurrentUser(user);
        }

        throw new UsernameNotFoundException("Could not find user with username:" + username);

        //return userService.loadUserByUsername(username);
    }

}
