package com.app.DAO.HibernateDao;

import com.app.HibernateModel.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;
import java.util.Set;

/**
 * Created by User on 22.06.2017.
 */
public class HibernateUserDao extends HibernateDAO<String, User> {

//    @Override
//    public void create(User entity) {
//        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(entity);
//        em.getTransaction().commit();
//    }

    @Override
    public Optional<User> read(String key) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, key);
        em.getTransaction().commit();
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(User entity) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.getReference(User.class, entity.getLogin()));
        em.getTransaction().commit();
    }
    @Override
    public Set<User> getAll() {
        return null;
    }
}
