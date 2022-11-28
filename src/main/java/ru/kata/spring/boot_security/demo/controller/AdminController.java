package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;


    @Autowired
    public AdminController(UserService service) {
        this.service = service;

    }


    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", Role.values());
        return "addUser";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam Set<Role> roles) {
        user.setRoles(roles);
        service.saveNewUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", service.getUserById(id));

        return "user";
    }

    @PatchMapping("/{id}/makeadmin")
    public String makeAdmin(@PathVariable("id") int id) {
        service.makeAdmin(service.getUserById(id));
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam Set<Role> roles) {
        user.setRoles(roles);
        service.saveNewUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.deleteUserById(id);
        return "redirect:/admin/users";
    }
}