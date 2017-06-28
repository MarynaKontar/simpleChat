package com.app.DAO.HibernateDao;


import com.app.HibernateModel.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

/**
 * Created by User on 27.06.2017.
 */
public class HibernateMessageDao extends HibernateDAO<Long, Message> {
    @Override
    public Optional<Message> read(Long key) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Message entity = em.find(Message.class, key);
        em.getTransaction().commit();
        return Optional.ofNullable(entity);
    }
}
