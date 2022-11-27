package ru.kata.spring.boot_security.demo.web.service;
import ru.kata.spring.boot_security.demo.web.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    void addRole(Role role);
    List<Role> getAllRoles();
}
