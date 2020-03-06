package com.webshop.dao.daoImpl;

import com.webshop.dao.OrderDetailsDAO;
import com.webshop.entity.OrderDetailsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class OrderDetailsDAOImpl extends CommonDAOImpl implements OrderDetailsDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Long findLastOrderNumberFromUser(Long userId) {
        Long lastOrderNumber = 0L;
            lastOrderNumber = (Long) em.createQuery("SELECT coalesce(max(O.orderNumber),0) FROM OrderDetailsEntity O JOIN O.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        return lastOrderNumber;
    }

    @Override
    public List<OrderDetailsEntity> findOrderByUserId(Long userId) {
        List<OrderDetailsEntity> orderList = null;
            orderList = (List<OrderDetailsEntity>) em.createQuery("select O from OrderDetailsEntity O join O.user U where U.id=:userId order by O.orderNumber")
                    .setParameter("userId", userId).getResultList();
        return orderList;
    }

    @Override
    public List<OrderDetailsEntity> findOrderDetailsByUserIdAndOrderNUmber(Long userId, Long orderNumber) {
        List<OrderDetailsEntity> orderList = null;
            orderList = (List<OrderDetailsEntity>) em.createQuery("select O from OrderDetailsEntity O join O.user U where U.id=:userId and O.orderNumber=:orderNumber order by O.orderNumber")
                    .setParameter("userId", userId).setParameter("orderNumber", orderNumber).getResultList();
        return orderList;
    }
}
