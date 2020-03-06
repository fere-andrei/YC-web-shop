package com.webshop.dao.daoImpl;


import com.webshop.dao.MyCartDAO;
import com.webshop.entity.MyCartEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MyCartDAOImpl extends CommonDAOImpl implements MyCartDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long findNumberOfItems(Long userId) {
        Long numberOfItems = 0L;
            numberOfItems = (Long) em.createQuery("SELECT coalesce(sum(C.quantity),0) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        return numberOfItems;
    }

    @Override
    public List<MyCartEntity> findSpecificCartByUser(Long userId) {
        List<MyCartEntity> myCartEntity = em.createQuery("select C from MyCartEntity C join C.user U where U.id=:userId", MyCartEntity.class)
                    .setParameter("userId", userId).getResultList();

        return myCartEntity;
    }


    @Override
    public Double totalCostOfMyCart(Long userId) {
        Double totalPrice = 0.0;
            totalPrice = (Double) em.createQuery("SELECT coalesce(sum(C.price),0) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        return totalPrice;
    }

    @Override
    public MyCartEntity findProductFromCart(Long userId, Long productId) {
        MyCartEntity myCartEntity = new MyCartEntity();
            myCartEntity = em.createQuery("select C from MyCartEntity C join C.user U where U.id=:userId and C.id=:productId", MyCartEntity.class)
                    .setParameter("userId", userId).setParameter("productId", productId).getSingleResult();
        return myCartEntity;
    }

}
