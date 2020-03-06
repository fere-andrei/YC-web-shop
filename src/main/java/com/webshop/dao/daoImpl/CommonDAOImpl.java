package com.webshop.dao.daoImpl;

import com.webshop.dao.CommonDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.webshop.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommonDAOImpl<T> implements CommonDAO<T> {

    @PersistenceContext
    EntityManager em;

    @Override
    public void saveEntity(T entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntity(T entity) {
        em.merge(entity);
    }

    @Override
    public void deleteEntity(T entity) {
        em.remove(entity);
    }
}