package ru.kata.spring.boot_security.demo.web.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.web.model.Role;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.RoleService;
import ru.kata.spring.boot_security.demo.web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {


    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    private RoleService roleService;

    public ApplicationRunnerImpl(UserService userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
            Role admin = roleService.getRoleById(1L);
            Role user = roleService.getRoleById(2L);
            Set<Role> adminRole = new HashSet<>();
            Set<Role> userRole = new HashSet<>();
            adminRole.add(admin);
            userRole.add(user);
            userService.saveNewUser(new User("thirdreichfromthesun@outlook.com", "Adolph", "Fuhrer", passwordEncoder.encode("admin"), adminRole ));
            userService.saveNewUser(new User( "damspace@yandex.ru", "DimaTerminator", "Wannabe", passwordEncoder.encode("user"), userRole ));
        }
    }
}