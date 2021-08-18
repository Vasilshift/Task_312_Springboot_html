package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.findAll();
        return allUsers;
    }
}
