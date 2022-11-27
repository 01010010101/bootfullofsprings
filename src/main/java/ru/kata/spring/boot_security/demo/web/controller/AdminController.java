package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.model.Role;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.web.service.UserServiceImp;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {


    private UserServiceImp userService;

    private RoleServiceImp roleService;

    private BCryptPasswordEncoder passwordEncoder;

    public AdminController(UserServiceImp userService, RoleServiceImp roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/allUsers")
    public String allUsers(@AuthenticationPrincipal User user, Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());

        return "allUsers";
    }
    @GetMapping(value = "create")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }
    @PostMapping(value = "add")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(required=false) String roleAdmin) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(2L));
        if (roleAdmin != null && roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(roleService.getRoleById(1L));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveNewUser(user);

        return "redirect:/admin/allUsers";
    }


    @GetMapping (value = "/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }
    @PatchMapping (value = "/edit/{id}")
    public String userUpdate(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUser(user);
        return "redirect:/admin/allUsers";
    }



    @DeleteMapping ("{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }
}