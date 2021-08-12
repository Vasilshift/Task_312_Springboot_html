package web.controller;

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

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "roleView") String[] roleView
                            ) {
        //model.addAttribute("allRoles", appService.findAllRoles());
        userService.addRolesToUser(user, roleView);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-delete-form";
    }


    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "roleView") String[] roleView){
        user.setRoles(roleService.updateRoles(roleView));
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
