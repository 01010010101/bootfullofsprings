package ru.kata.spring.boot_security.demo.web.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.web.model.Role;
import ru.kata.spring.boot_security.demo.web.repository.RoleRepository;

import java.util.List;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void addRole(Role role) {
        roleRepository.addRole(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Transactional(readOnly=true)
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }
    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

}