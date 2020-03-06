package com.webshop.dao.daoImpl;

import com.webshop.dao.OrderDAO;
import com.webshop.entity.OrderEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class OrderDAOImpl extends CommonDAOImpl implements OrderDAO {

    @PersistenceContext
    EntityManager em;


    @Override
    public List<OrderEntity> findOrdersByUser(Long userId) {
        List<OrderEntity> orderList = null;

            orderList = (List<OrderEntity>) em.createQuery("select O from OrderEntity O join O.user U where U.id=:userId order by O.orderNumber")
                    .setParameter("userId", userId).getResultList();

        return orderList;
    }
}
