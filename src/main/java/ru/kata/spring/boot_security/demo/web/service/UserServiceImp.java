package ru.kata.spring.boot_security.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.web.dao.UserDao;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.repository.UserRepository;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private UserRepository userRepository;


    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    private final UserDao userDao;
    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Transactional(readOnly = false)
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }
    @Transactional(readOnly = false)
    public void saveNewUser(User user) {
        userDao.saveNewUser(user);
    }
    @Transactional(readOnly = false)
    public void updateUserById(int id, User updatedUser) { userDao.updateUserById(id, updatedUser);}
    public User getUserById(int id) {
       return userDao.getUserById(id);
    }
}
