package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ControllerAdvice
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;

    }

    private Map<Long, User> userMap = new HashMap<>();

    @RequestMapping(value = "/addusertest", method = {RequestMethod.GET, RequestMethod.PUT})
    public String submit(
            @ModelAttribute("user") User user,
            BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("username", user.getUsername());
//        model.addAttribute("password", user.getPassword());
//        model.addAttribute("lastname", user.getLastname());
//        model.addAttribute("age", user.getAge());
//        model.addAttribute("email", user.getEmail());
//        model.addAttribute("roles", user.getRoles());

        userMap.put(user.getId(), user);

        return "testpage";
    }



    @GetMapping(value = "/")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/admin")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "roleAdmin") String roleAdmin
                            ) {
        if (roleAdmin != null) {
            userService.saveUser(userService.addRoleToUser(user, userService.getRoleByRolename("ROLE_ADMIN")));
        } else {
            userService.saveUser(userService.addRoleToUser(user, userService.getRoleByRolename("ROLE_USER")));
        }
        return "redirect:/admin";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "roleAdmin") String roleAdmin){
        if (roleAdmin != null) {
            userService.saveUser(userService.addRoleToUser(user, userService.getRoleByRolename("ROLE_ADMIN")));
        } else {
            userService.saveUser(userService.addRoleToUser(user, userService.getRoleByRolename("ROLE_USER")));
        }

        //userService.saveUser(user);
        return "redirect:/admin";
    }
}
