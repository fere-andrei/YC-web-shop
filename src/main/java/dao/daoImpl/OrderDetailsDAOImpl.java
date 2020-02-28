package dao.daoImpl;

import dao.OrderDetailsDAO;
import entity.OrderDetailsEntity;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class OrderDetailsDAOImpl extends CommonDAOImpl implements OrderDetailsDAO {


    @Override
    public Long findLastOrderNumberFromUser(Long userId) {
        Long lastOrderNumber = 0L;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            lastOrderNumber = (Long) session.createQuery("SELECT coalesce(max(O.orderNumber),0) FROM OrderDetailsEntity O JOIN O.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastOrderNumber;
    }

    @Override
    public List<OrderDetailsEntity> findOrderByUserId(Long userId) {
        List<OrderDetailsEntity> orderList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderList = (List<OrderDetailsEntity>) session.createQuery("select O from OrderDetailsEntity O join O.user U where U.id=:userId order by O.orderNumber")
                    .setParameter("userId", userId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<OrderDetailsEntity> findOrderDetailsByUserIdAndOrderNUmber(Long userId, Long orderNumber) {
        List<OrderDetailsEntity> orderList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderList = (List<OrderDetailsEntity>) session.createQuery("select O from OrderDetailsEntity O join O.user U where U.id=:userId and O.orderNumber=:orderNumber order by O.orderNumber")
                    .setParameter("userId", userId).setParameter("orderNumber", orderNumber).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
