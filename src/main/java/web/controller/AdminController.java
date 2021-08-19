package web.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bcryptpasswordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, BCryptPasswordEncoder bcryptpasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bcryptpasswordEncoder = bcryptpasswordEncoder;
    }

    @GetMapping()
    public String findAll(Model model){
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("allRoles", roles);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(@ModelAttribute("user") User user, Model model ) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("allUsers", roleService.findAllRoles());
        return "user-delete-form";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "user-update";
    }

    @PutMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        user.setPassword(bcryptpasswordEncoder.encode(user.getPassword()));
        model.addAttribute("allRoles", roleService.findAllRoles());
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
