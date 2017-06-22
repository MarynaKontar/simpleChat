package com.app.DAO.HibernateDao;

import com.app.DAO.DAOUser;
import com.app.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 22.06.2017.
 */
public class HibernateUserDao implements DAOUser{
    @Override
    public void deleteByKey(String key) {

    }

    @Override
    public void create(User entity) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public Optional<User> read(String key) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
