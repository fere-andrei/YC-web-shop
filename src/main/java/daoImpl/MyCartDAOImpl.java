package daoImpl;

import dao.MyCartDAO;
import entity.MyCartEntity;
import entity.ProductsEntity;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class MyCartDAOImpl implements MyCartDAO {
    @Override
    public void saveMyCart(MyCartEntity myCartEntity) {

    }

    @Override
    public int findNumberOfItems(int userId) {
        int numberOfItems = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            numberOfItems = (int) session.createQuery("SELECT COUNT(c.id) FROM MyCartEntity C JOIN C.user U WHERE U.id=:userId").setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfItems;
    }

    @Override
    public List<MyCartEntity> findSpecificCartByUser(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from MyCartEntity C join c.user u where u.id=:userId", MyCartEntity.class).setParameter("userId", userId).getResultList();
        }
    }
}
