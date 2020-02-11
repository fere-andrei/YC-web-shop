package daoImpl;

import dao.OrderDAO;
import entity.OrderDetailsEntity;
import entity.OrderEntity;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class OrderDAOImpl extends CommonDAOImpl implements OrderDAO {


    @Override
    public List<OrderEntity> findOrdersByUser(Long userId) {
        List<OrderEntity> orderList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            orderList = (List<OrderEntity>) session.createQuery("select O from OrderEntity O join O.user U where U.id=:userId order by O.orderNumber")
                    .setParameter("userId", userId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
