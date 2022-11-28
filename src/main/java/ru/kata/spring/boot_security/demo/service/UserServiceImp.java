package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public void saveNewUser(User user) {

    }

    @Override
    public void updateUserById(int id, User updatedUser) {

    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    @Override
    public void makeAdmin(User user) {
        user.addRole(Role.ADMIN);
        repository.save(user);
    }
}
