package ru.kata.spring.boot_security.demo.web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getAllUsers() {
        String jpql = "SELECT c FROM User c";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }
    public void deleteUserById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    @Override
    public void saveNewUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void updateUserById(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }
    @Override
    public User getUserById(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}

