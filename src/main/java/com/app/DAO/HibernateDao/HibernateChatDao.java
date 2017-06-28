package com.app.DAO.HibernateDao;

import com.app.HibernateModel.Chat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;
import java.util.Set;

/**
 * Created by User on 27.06.2017.
 */
public class HibernateChatDao extends HibernateDAO<String, Chat> {
//    @Override
//    public void create(Chat entity) {
//        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(entity);
//        em.getTransaction().commit();
//    }

    @Override
    public Optional<Chat> read(String key) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Chat entity = em.find(Chat.class, key);
        em.getTransaction().commit();
        return Optional.ofNullable(entity);
    }

    }
