package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;


@Controller
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }    @GetMapping("/users")
    public String index1(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }
    @PostMapping
    public String createUser(@ModelAttribute ("user") User user){
        userService.saveNewUser(user);
        return "redirect:/";
    }
    @GetMapping(value = "/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }
    @PostMapping(value = "/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(id, user);
        return "redirect:/users";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
    @PostMapping("/delete")
    public String deleteIfExists(@PathVariable User user) {
        userService.deleteUserById(user.getId());
        return "redirect:/users";
    }
}
