package ru.kata.spring.boot_security.demo.web.service;


import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void deleteUserById(int id);
    void saveNewUser(User user);
    void updateUserById(int id, User updatedUser);
    User getUserById(int id);
}
