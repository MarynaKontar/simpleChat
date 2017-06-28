package com.app.DAO.HibernateDao;

import com.app.HibernateModel.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

/**
 * Created by User on 27.06.2017.
 */
public class HibernateGroupDao extends HibernateDAO<Long, Group>{
    @Override
    public Optional<Group> read(Long key) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Group entity = em.find(Group.class, key);
        em.getTransaction().commit();
        return Optional.ofNullable(entity);
    }
}
