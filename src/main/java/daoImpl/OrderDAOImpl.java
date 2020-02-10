package daoImpl;

import dao.OrderDAO;
import entity.MyCartEntity;
import entity.OrderEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class OrderDAOImpl extends CommonDAOImpl  implements OrderDAO{


    @Override
    public Long lastOrderNumberFromUser(Long userId) {
        Long lastOrderNumber = 0L;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            lastOrderNumber = (Long) session.createQuery("SELECT max(O.orderNumber) FROM OrderEntity O JOIN C.user U WHERE U.id=:userId")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastOrderNumber;
    }
}
