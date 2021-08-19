package web.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;

    private final RoleService roleService;

    //private final InitServiceImpl initServiceImpl;

    @Autowired
    public MyRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

//    @PostConstruct
//    public void firstInitialization() {
//        initServiceImpl.initServiceImpl();
//    }

    @GetMapping("/users")
    public List<User> apiGetAllUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User user = userService.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(new File("c://temp/user.json"), User.class);
        } catch (JsonGenerationException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }



//        if(user == null){
//            throw new NoSuchUserException("There is no user with id "+ id);
//        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> add(@RequestBody User user){
        //roleService.updateRoles();
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user){
        //roleService.setupRoles(user);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id) {

        //User user = userService.findById(id);

//        if(user == null){
//            throw new NoSuchUserException("There is no user with id "+ id);
//        }
        userService.deleteById(id);
    }
}
