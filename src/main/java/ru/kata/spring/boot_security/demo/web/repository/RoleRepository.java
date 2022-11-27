package ru.kata.spring.boot_security.demo.web.repository;

import ru.kata.spring.boot_security.demo.web.model.Role;

import java.util.List;


public interface RoleRepository {

    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();

    void addRole(Role role);
}